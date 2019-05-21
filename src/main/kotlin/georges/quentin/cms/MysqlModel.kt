package georges.quentin.cms

import georges.quentin.cms.model.Article
import georges.quentin.cms.model.Comment

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
            connection.prepareStatement("SELECT id, title FROM articles").use { stmt ->
                stmt.executeQuery().use { result ->
                    while (result.next()) {
                        list += Article(
                            result.getInt("id"),
                            result.getString("title"),
                            null,
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
}