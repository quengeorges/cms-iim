package georges.quentin.cms.tpl

import georges.quentin.cms.model.Article

data class ArticleContext(
    val Article: Article?,
    val Connect: Boolean
)