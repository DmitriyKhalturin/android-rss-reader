package dmitriykhalturin.rssreader.presenter

import com.khalturin.dmitriy.domain.interactor.GetArticlesList
import com.khalturin.dmitriy.domain.interactor.UpdateArticlesList
import com.khalturin.dmitriy.domain.vo.Article
import dmitriykhalturin.rssreader.RssReaderApplication
import dmitriykhalturin.rssreader.state.Navigate
import dmitriykhalturin.rssreader.view.viewmodel.FeedViewModel
import dmitriykhalturin.rssreader.view.viewmodel.mapper.CardArticleMapper
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 0:59.
 */
class FeedPresenter : BasePresenter<FeedViewModel>(FeedViewModel::class.java) {

  private val mComponent by lazy { RssReaderApplication.getInjector().getsApplicationComponent() }

  @Inject lateinit var mNavigate: Navigate

  @Inject lateinit var mGetArticlesList: GetArticlesList

  @Inject lateinit var mUpdateArticlesList: UpdateArticlesList

  @Inject lateinit var mCardArticleMapper: CardArticleMapper

  init {
    mComponent.inject(this)

    addDisposables(
      mGetArticlesList.execute(null).subscribe(this::articlesUpdate, this::onException)
    )
  }

  override fun buildViewModel() {
    addDisposables(
      mViewModel.getOnReadArticle().subscribe(this::readArticle)
    )
  }

  fun navigateToBookmarks() = mNavigate.navigateToBookmarks()

  private fun articlesUpdate(articles: MutableList<Article>) {
    val recyclerViewManager = mViewModel.mRecyclerViewManager.get()

    recyclerViewManager?.mAdapter?.setItems(mCardArticleMapper.transform(articles, mViewModel))
  }

  private fun readArticle(articleId: Long) {
    mNavigate.navigateToArticle(articleId)
  }

  private fun onException(throwable: Throwable) {

  }

}
