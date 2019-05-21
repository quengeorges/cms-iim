package georges.quentin.cms

import freemarker.cache.ClassTemplateLoader
import georges.quentin.cms.model.Article
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
import kotlinx.coroutines.launch

class App

fun main() {

    val appComponents = AppComponents("jdbc:mysql://localhost/CMS?serverTimezone=UTC", "root", "root")

    embeddedServer(Netty, 8080) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
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
                            call.respond(FreeMarkerContent("index.ftl", context, "e"))
                        }
                    }
                })
                controller.start()
            }

            get("/admin") {

            }

            get("article/{id}") {
                val controller = appComponents.getArticlePresenterr(object: ArticlePresenter.View {
                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }

                    override fun displayArticle(article: Article?) {
                        launch {
                            print(article?.comments)
                            call.respond(FreeMarkerContent("article.ftl", article, "e"))
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

                val controller = appComponents.createComment(object: CommentController.View {
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
        }
    }.start(wait = true)
}