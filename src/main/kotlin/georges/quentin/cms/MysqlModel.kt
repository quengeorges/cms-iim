package georges.quentin.cms

import georges.quentin.cms.model.Article

class MysqlModel(val pool: ConnectionPool) : Model {

    override fun getArticleList(): List<Article> {
        val list = ArrayList<Article>()

        pool.useConnection { connection ->
            connection.prepareStatement("SELECT id, title FROM articles").use { stmt ->
                stmt.executeQuery().use { result ->
                    while (result.next()) {
                        list += Article(
                            result.getInt("id"),
                            result.getString("title"),
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
                            result.getString("text")
                        )
                    }
                }
            }
        }
        return null
    }
}