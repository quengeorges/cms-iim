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

    override fun deleteArticle(id: Int) {
        val deleteStatus = model.deleteArticle(id)
        if (deleteStatus) {
            view.success()
        } else {
            view.error()
        }
    }

    override fun createArticle(title: String?, content: String?) {
        if (content != null && title != null) {
            val article = model.createArticle(title, content)
            if (article) {
                view.success()
            } else {
                view.error()
            }
        } else {
            view.error()
        }
    }
}