package georges.quentin.cms

import freemarker.cache.ClassTemplateLoader
import georges.quentin.cms.model.Article
import georges.quentin.cms.model.AuthSession
import georges.quentin.cms.model.User
import georges.quentin.cms.tpl.IndexContext
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import kotlinx.coroutines.launch

class App

fun main() {

    val appComponents = AppComponents("jdbc:mysql://localhost/CMS?serverTimezone=UTC", "root", "root")

    embeddedServer(Netty, 8080) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        install(Sessions) {
            cookie<AuthSession>("AUTH_COOKIE") {
                cookie.path = "/"
            }
        }

        routing {
            static("static") {
                resources("static")
            }

            get("/") {
                val controller = appComponents.getArticleListPresenter(object: ArticleListPresenter.View {
                    override fun dispalyArticleList(list: List<Article>) {
                        val context = IndexContext(list)
                        launch {
                            if (call.sessions.get<AuthSession>() != null) {
                                call.respond(FreeMarkerContent("admin/index_admin.ftl", context, "e"))
                            } else {
                                call.respond(FreeMarkerContent("index.ftl", context, "e"))
                            }
                        }
                    }
                })
                controller.start()
            }

            get("/login") {
                call.respond(FreeMarkerContent("login.ftl", null, "e"))
            }
            post("/login") {
                val requestBody = call.receiveParameters()
                val mail = requestBody["mail"]
                val password = requestBody["password"]
                val endSession = System.currentTimeMillis() + 600000;

                val controller =appComponents.login(object: AuthController.View {
                    override fun success(user: User) {
                        launch {
                            call.sessions.set(AuthSession(user.mail, endSession))

                            if(call.sessions.get<AuthSession>() != null) {
                                call.respondRedirect("/")
                            } else {
                                call.respondText("Something wrong happened")
                            }
                        }
                    }

                    override fun error() {
                        launch {
                            call.respondText { "Invalid credentials "}
                        }
                    }
                })
                controller.login(mail, password)
            }

            get("/logout") {
                call.sessions.clear("AUTH_COOKIE")
                call.respondRedirect("/")
            }

            get("/admin/article/form") {
                call.respond(FreeMarkerContent("admin/article_admin_form.ftl", null, "e"))
            }

            post("/admin/article/add") {
                val requestBody = call.receiveParameters()
                val title = requestBody["title"]
                val content = requestBody["content"]

                val controller = appComponents.getArticleController(object: ArticleController.View {
                    override fun success() {
                        launch {
                            call.respondRedirect("/")
                        }
                    }

                    override fun error() {
                        launch {
                            call.respondText("Something wrong happened")
                        }
                    }

                })

                controller.createArticle(title, content)
            }

            get("/admin/article/del/{id}") {
                val controller = appComponents.getArticleController(object: ArticleController.View {
                    override fun success() {
                        launch {
                            call.respondRedirect("/")
                        }
                    }

                    override fun error() {
                        launch {
                            call.respondText("Something wrong happened")
                        }
                    }
                })

                val id = call.parameters["id"]!!.toInt()
                controller.deleteArticle(id)
            }

            get("article/{id}") {
                val controller = appComponents.getArticlePresenter(object: ArticlePresenter.View {
                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }

                    override fun displayArticle(article: Article?) {
                        launch {
                            if(call.sessions.get<AuthSession>() != null) {
                                call.respond(FreeMarkerContent("admin/article_admin.ftl", article, "e"))
                            } else {
                                call.respond(FreeMarkerContent("article.ftl", article, "e"))
                            }
                        }
                    }
                })

                val id = call.parameters["id"]!!.toInt()
                controller.start(id)
            }

            post("/comment/add") {
                val requestBody = call.receiveParameters()
                val text = requestBody["text"]
                val articleId = requestBody["article_id"]

                val controller = appComponents.getCommentController(object: CommentController.View {
                    override fun error() {
                        launch {
                            call.respondText("Something wrong happened")
                        }
                    }

                    override fun success() {
                        launch {
                            call.respondRedirect("/article/$articleId")
                        }
                    }
                })

                controller.createComment(text, articleId!!.toInt())
            }

            get("/admin/comment/del/{comment_id}/{article_id}") {
                val articleId = call.parameters["article_id"]!!.toInt()
                val id = call.parameters["comment_id"]!!.toInt()

                val controller = appComponents.getCommentController(object: CommentController.View {
                    override fun success() {
                        launch {
                            call.respondRedirect("/article/${articleId}")
                        }
                    }

                    override fun error() {
                        launch {
                            call.respondText("Something wrong happened")
                        }
                    }
                })
                controller.deleteComment(id)
            }
        }
    }.start(wait = true)
}