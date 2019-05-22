package georges.quentin.cms.control

import georges.quentin.cms.ArticleController
import georges.quentin.cms.Model

class ArticleControllerImpl(val model: Model, val view: ArticleController.View): ArticleController {
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
        }

    }
}
