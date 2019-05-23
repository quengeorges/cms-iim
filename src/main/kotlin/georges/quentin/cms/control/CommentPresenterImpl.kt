package georges.quentin.cms.control

import georges.quentin.cms.CommentPresenter
import georges.quentin.cms.Model

class CommentPresenterImpl(val model: Model, val view: CommentPresenter.View): CommentPresenter {
    override fun createComment(content: String?, articleId: Int) {
        if (content != "") {
            val comment = model.createComment(content, articleId)
            if (comment) {
                view.success()
            } else {
                view.error()
            }
        } else {
            view.error()
        }
    }

    override fun deleteComment(id: Int) {
        val deleteStatus = model.deleteComment(id)
        if(deleteStatus) {
            view.success()
        } else {
            view.error()
        }
    }
}