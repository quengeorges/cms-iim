package georges.quentin.cms

interface CommentController {

    fun createComment(content: String?, articleId: Int)

    interface View {
        fun success()
        fun error()
    }
}