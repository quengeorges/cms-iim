package georges.quentin.cms

import georges.quentin.cms.model.Article

interface Model {

    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?
}