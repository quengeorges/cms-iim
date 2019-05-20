package georges.quentin.cms

import com.nhaarman.mockitokotlin2.*
import georges.quentin.cms.control.ArticleListPresenterImpl
import georges.quentin.cms.control.ArticlePresenterImpl
import georges.quentin.cms.model.Article
import org.junit.Test

class PresenterTests {

    @Test
    fun testArticleListPresenter(){
        val list = listOf(Article(1, "un", null), Article(2, "deux", null))

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
        val article = Article(1, "un", null)

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
}