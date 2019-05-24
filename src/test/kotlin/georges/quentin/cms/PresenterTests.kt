package georges.quentin.cms

import com.nhaarman.mockitokotlin2.*
import georges.quentin.cms.control.ArticleListPresenterImpl
import georges.quentin.cms.control.ArticlePresenterImpl
import georges.quentin.cms.control.AuthPresenterImpl
import georges.quentin.cms.control.CommentPresenterImpl
import georges.quentin.cms.model.Article
import georges.quentin.cms.model.Comment
import georges.quentin.cms.model.User
import org.junit.Test

class PresenterTests {

    @Test
    fun testArticleListPresenter(){
        val list = listOf(Article(1, "un", null, null), Article(2, "deux", null, null))

        val model = mock<Model> {
            on { getArticleList() } doReturn list
        }

        val view = mock<ArticleListPresenter.View>()

        val presenter = ArticleListPresenterImpl(model, view)
        presenter.start()

        verify(model).getArticleList()
        verify(view).dispalyArticleList(list)
        verifyNoMoreInteractions(model,view)
    }

    @Test
    fun testArticlePresenter(){
        val article = Article(1, "un", null, null)

        val model = mock<Model> {
            on { getArticle(1) } doReturn article
        }

        val view = mock<ArticlePresenter.View>()

        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(1)
        verify(model).getArticle(1)
        verify(view).displayNotFound()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testInvalidArticlePresenter(){
        val model = mock<Model> {
            on { getArticle(any()) } doReturn null
        }

        val view = mock<ArticlePresenter.View>()

        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(1)
        verify(model).getArticle(1)
        verify(view).displayNotFound()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testCreateCommentSuccess() {
        val commentText = "Lorem ipsum"
        val articleId = 1

        val model = mock<Model> {
            on { createComment(commentText, articleId) } doReturn true
        }

        val view = mock<CommentPresenter.View>()
        val presenter = CommentPresenterImpl(model, view)
        presenter.createComment(commentText, articleId)

        verify(model).createComment(commentText, articleId)
        verify(view).success()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testCreateCommentFail() {
        val commentText = "Lorem ipsum"
        val articleId = 1

        val model = mock<Model> {
            on { createComment(commentText, articleId) } doReturn false
        }

        val view = mock<CommentPresenter.View>()
        val presenter = CommentPresenterImpl(model, view)
        presenter.createComment(commentText, articleId)

        verify(model).createComment(commentText, articleId)
        verify(view).error()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testCreateArticleSuccess() {
        val title = "title lorem ipsum"
        val content = "lorem ipsum dolor sit amet"

        val model = mock<Model> {
            on { createArticle(title, content) } doReturn true
        }

        val view = mock<ArticlePresenter.View>()
        val presenter = ArticlePresenterImpl(model, view)
        presenter.createArticle(title, content)

        verify(model).createArticle(title, content)
        verify(view).success()
        verifyNoMoreInteractions(model, view)
    }


    @Test
    fun testCreateArticleFail() {
        val title = "title lorem ipsum"
        val content = "lorem ipsum dolor sit amet"

        val model = mock<Model> {
            on { createArticle(title, content) } doReturn false
        }

        val view = mock<ArticlePresenter.View>()
        val presenter = ArticlePresenterImpl(model, view)
        presenter.createArticle(title, content)

        verify(model).createArticle(title, content)
        verify(view).error()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testDeleteCommentSuccess() {
        val commentId = 1

        val model = mock<Model> {
            on { deleteComment(commentId)} doReturn true
        }

        val view = mock<CommentPresenter.View>()
        val presenter = CommentPresenterImpl(model, view)
        presenter.deleteComment(commentId)

        verify(model).deleteComment(commentId)
        verify(view).success()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testDeleteCommentFail() {
        val commentId = 1

        val model = mock<Model> {
            on { deleteComment(commentId) } doReturn false
        }

        val view = mock<CommentPresenter.View>()
        val presenter = CommentPresenterImpl(model, view)
        presenter.deleteComment(commentId)

        verify(model).deleteComment(commentId)
        verify(view).error()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testDeleteArticleSuccess() {
        val articleId = 1

        val model = mock<Model> {
            on { deleteArticle(articleId) } doReturn true
        }

        val view = mock<ArticlePresenter.View>()
        val presenter = ArticlePresenterImpl(model, view)
        presenter.deleteArticle(articleId)

        verify(model).deleteArticle(articleId)
        verify(view).success()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testDeleteArticleError() {
        val articleId = 1

        val model = mock<Model> {
            on { deleteArticle(articleId) } doReturn false
        }

        val view = mock<ArticlePresenter.View>()
        val presenter = ArticlePresenterImpl(model, view)
        presenter.deleteArticle(articleId)

        verify(model).deleteArticle(articleId)
        verify(view).success()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testLoginSuccess() {
        val mail = "jean@jean.jean"
        val password = "jean"
        val user = User("jean@jean.jean", "jean")

        val model = mock<Model> {
            on { getUser(mail, password) } doReturn user
        }

        val view = mock<AuthPresenter.View>()
        val presenter = AuthPresenterImpl(model, view)
        presenter.login(mail, password)

        verify(model).getUser(mail, password)
        verify(view).success(user)
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testLoginFail() {
        val mail = "jean@jean.jean"
        val password = "jean"

        val model = mock<Model> {
            on { getUser(mail, password) } doReturn null
        }

        val view = mock<AuthPresenter.View>()
        val presenter = AuthPresenterImpl(model, view)
        presenter.login(mail, password)

        verify(model).getUser(mail, password)
        verify(view).error()
        verifyNoMoreInteractions(model, view)
    }
}