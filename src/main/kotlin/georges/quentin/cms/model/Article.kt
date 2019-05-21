package georges.quentin.cms.model

class Article(
    val id: Int,
    val title: String,
    val text: String?,
    val comments: List<Comment>?
)