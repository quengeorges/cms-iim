package georges.quentin.cms.control

import georges.quentin.cms.CommentController
import georges.quentin.cms.Model

class CommentControllerImpl(val model: Model, val view: CommentController.View): CommentController {
    override fun createComment(content: String?, article_id: Int) {
        if (content != "") {
            val comment = model.createComment(content, article_id)
            if (comment && content != "") {
                view.success()
            } else {
                view.error()
            }
        } else {
            view.error()
        }
    }

}