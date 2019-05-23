package georges.quentin.cms

import georges.quentin.cms.control.*

class AppComponents(val mySqlUrl: String, val mySqlUser: String, val mySqlPassword: String) {

    private val pool = ConnectionPool(mySqlUrl, mySqlUser, mySqlPassword)

    private val model = MysqlModel(getPool())

    fun getPool(): ConnectionPool {
        return pool
    }

    fun getModel(): Model {
        return model
    }

    fun getArticleListPresenter(view: ArticleListPresenter.View): ArticleListPresenter {
        return ArticleListPresenterImpl(getModel(), view)
    }

    fun getArticlePresenter(view: ArticlePresenter.View): ArticlePresenter {
        return ArticlePresenterImpl(getModel(), view)
    }

    fun getCommentController(view: CommentPresenter.View): CommentPresenter {
        return CommentPresenterImpl(getModel(), view)
    }

    fun login(view: AuthPresenter.View): AuthPresenter {
        return AuthPresenterImpl(getModel(), view)
    }
}