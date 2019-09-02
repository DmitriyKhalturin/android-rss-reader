package dmitriykhalturin.rssreader.presenter

import com.khalturin.dmitriy.domain.interactor.GetArticle
import com.khalturin.dmitriy.domain.vo.Article
import dmitriykhalturin.rssreader.RssReaderApplication
import dmitriykhalturin.rssreader.constant.Pattern
import dmitriykhalturin.rssreader.state.Navigate
import dmitriykhalturin.rssreader.view.viewmodel.ArticleViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 0:59.
 */
class ArticlePresenter : BasePresenter<ArticleViewModel>(ArticleViewModel::class.java) {

  companion object {

    const val UNKNOWN_ARTICLE_ID = -1L

  }

  @Inject lateinit var mNavigate: Navigate

  @Inject lateinit var mGetArticle: GetArticle

  private val mComponent by lazy { RssReaderApplication.getInjector().getsApplicationComponent() }

  init {
    mComponent.inject(this)
  }

  override fun buildViewModel() {
    addDisposables(
      mViewModel.getOnOpenLink().subscribe(this::openLink)
    )
  }

  fun setArticleId(articleId: Long) {
    if(articleId != UNKNOWN_ARTICLE_ID) {
      addDisposables(
        mGetArticle.execute(articleId).subscribe(this::articleUpdate, this::onException)
      )
    }
  }

  private fun openLink(link: String) {
    mNavigate.openInBrowser(link)
  }

  private fun articleUpdate(article: Article) {
    val dateFormat = SimpleDateFormat(Pattern.DATE_FORMAT, Locale.getDefault())

    mViewModel.mId.set(article.mId)
    mViewModel.mTitle.set(article.mTitle)
    mViewModel.mAuthor.set(article.mAuthor)
    mViewModel.mDescription.set(article.mDescription)
    mViewModel.mContent.set(article.mContent)
    mViewModel.mImage.set(article.mImage)
    mViewModel.mLink.set(article.mLink)
    mViewModel.mPublicationDate.set(dateFormat.format(article.mPublicationDate))
    mViewModel.mIsWasRead.set(article.mIsWasRead)
  }

  private fun onException(throwable: Throwable) {

  }

}
