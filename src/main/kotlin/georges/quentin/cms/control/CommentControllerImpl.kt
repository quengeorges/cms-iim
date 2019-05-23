package georges.quentin.cms.control

import georges.quentin.cms.CommentController
import georges.quentin.cms.Model

class CommentControllerImpl(val model: Model, val view: CommentController.View): CommentController {
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