package georges.quentin.cms

import georges.quentin.cms.model.Article

interface ArticleListPresenter {

    fun start()

    interface View {
        fun dispalyArticleList(list: List<Article>)
    }

}