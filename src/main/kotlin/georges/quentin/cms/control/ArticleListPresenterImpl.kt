package georges.quentin.cms.control

import georges.quentin.cms.ArticleListPresenter
import georges.quentin.cms.Model

class ArticleListPresenterImpl(val model: Model, val view: ArticleListPresenter.View): ArticleListPresenter {

    override fun start() {
        val list = model.getArticleList()
        view.dispalyArticleList(list)
    }
}