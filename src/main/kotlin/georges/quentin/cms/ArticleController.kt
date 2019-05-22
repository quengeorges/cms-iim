package georges.quentin.cms

interface ArticleController {

    fun deleteArticle(id: Int)

    fun createArticle(title: String?, content: String?)

    interface View {
        fun success()
        fun error()
    }
}