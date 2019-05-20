package georges.quentin.cms

interface CommentController {

    fun createComment(content: String?, article_id: Int)

    interface View {
        fun success()
        fun error()
    }
}