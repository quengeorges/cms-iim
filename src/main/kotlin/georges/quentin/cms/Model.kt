package georges.quentin.cms

import georges.quentin.cms.model.Article
import georges.quentin.cms.model.Comment

interface Model {

    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?

    fun createComment(content:String?, articleId: Int): Boolean

    fun getComments(articleId: Int): List<Comment>
}