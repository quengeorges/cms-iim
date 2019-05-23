package georges.quentin.cms

import georges.quentin.cms.model.Article

interface ArticlePresenter {

    fun start(id: Int)
    fun deleteArticle(id: Int)
    fun createArticle(title: String?, content: String?)

    interface View {
        fun displayArticle(article: Article?)
        fun displayNotFound()
        fun success()
        fun error()
    }
}