package georges.quentin.cms

import georges.quentin.cms.model.Article
import georges.quentin.cms.model.Comment
import georges.quentin.cms.model.User

class MysqlModel(val pool: ConnectionPool) : Model {

    override fun createComment(content: String?, articleId: Int): Boolean {
        pool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO commentaire (text, idArticle) VALUES (?, ?)").use { stmt ->
                stmt.setString(1, content)
                stmt.setInt(2, articleId)
                stmt.executeUpdate()
                return true
            }
        }
        return false
    }

    override fun getComments(articleId: Int): List<Comment> {
        val list = ArrayList<Comment>()
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT id, text, idArticle FROM commentaire WHERE idArticle= ?").use { stmt ->
                stmt.setInt(1, articleId)

                stmt.executeQuery().use {result ->
                    while(result.next()) {
                        list += Comment(
                            result.getInt("id"),
                            result.getString("text"),
                            result.getInt("idArticle")
                        )
                    }
                }
            }
        }
        return list
    }

    override fun getArticleList(): List<Article> {
        val list = ArrayList<Article>()

        pool.useConnection { connection ->
            connection.prepareStatement("SELECT id, title, text FROM articles").use { stmt ->
                stmt.executeQuery().use { result ->
                    while (result.next()) {
                        list += Article(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("text"),
                            null
                        )
                    }

                }
            }
        }
        return list
    }

    override fun getArticle(id: Int): Article? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)

                stmt.executeQuery().use { result ->
                    if(result.next()) {
                        return Article(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("text"),
                            getComments(result.getInt("id"))
                        )
                    }
                }
            }
        }
        return null
    }

    override fun getUser(mail: String, password: String): User? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE mail = ? AND password = ?").use { stmt ->
                stmt.setString(1, mail)
                stmt.setString(2, password)

                stmt.executeQuery().use { result ->
                    return if(result.next()) {
                        User(
                            result.getString("mail"),
                            result.getString("password")
                        )
                    } else {
                        null
                    }
                }
            }
        }
        return null
    }

    override fun deleteArticle(id: Int): Boolean {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM articles WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
                return true
            }
        }
        return false
    }

    override fun createArticle(title: String, content: String): Boolean {
        pool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO articles (title, text) VALUES (?, ?)").use { stmt ->
                stmt.setString(1, title)
                stmt.setString(2, content)
                stmt.executeUpdate()
                return true
            }
        }
        return false
    }

    override fun deleteComment(id: Int): Boolean {
        print(id)
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM commentaire WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
                return true
            }
        }
        return false
    }
}