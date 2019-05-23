package georges.quentin.cms

interface CommentController {

    fun createComment(content: String?, articleId: Int)

    fun deleteComment(id: Int)

    interface View {
        fun success()
        fun error()
    }
}