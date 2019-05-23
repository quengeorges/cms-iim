package georges.quentin.cms

import georges.quentin.cms.model.Article
import georges.quentin.cms.model.Comment
import georges.quentin.cms.model.User

interface Model {

    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?

    fun createComment(content:String?, articleId: Int): Boolean

    fun getComments(articleId: Int): List<Comment>

    fun getUser(mail: String, password: String): User?

    fun deleteArticle(id: Int): Boolean

    fun createArticle(title: String, content: String): Boolean

    fun deleteComment(id: Int): Boolean
}