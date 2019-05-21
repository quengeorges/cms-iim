package georges.quentin.cms.model

import java.io.Serializable

data class Comment(
    val id: Int,
    val content: String,
    val idArticle: Int
) : Serializable