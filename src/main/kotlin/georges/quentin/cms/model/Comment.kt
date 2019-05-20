package georges.quentin.cms.model

import java.io.Serializable

data class Comment(
    val id: Int,
    val content: String,
    val id_article: Int
) : Serializable