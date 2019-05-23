package georges.quentin.cms

interface CommentPresenter {

    fun createComment(content: String?, articleId: Int)

    fun deleteComment(id: Int)

    interface View {
        fun success()
        fun error()
    }
}