package georges.quentin.cms

import georges.quentin.cms.model.Article

interface ArticlePresenter {

    fun start(id: Int)

    interface View {
        fun displayArticle(article: Article?)
        fun displayNotFound()
    }
}