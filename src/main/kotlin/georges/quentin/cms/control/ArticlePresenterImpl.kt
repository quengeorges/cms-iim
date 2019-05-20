package georges.quentin.cms.control

import georges.quentin.cms.ArticlePresenter
import georges.quentin.cms.Model

class ArticlePresenterImpl(val model: Model, val view: ArticlePresenter.View): ArticlePresenter {

    override fun start(id: Int) {
        val article = model.getArticle(id)
        if (article != null) {
            view.displayArticle(article)
        } else {
            view.displayNotFound()
        }

    }
}