package dmitriykhalturin.rssreader.presenter

import com.khalturin.dmitriy.domain.interactor.AddFeed
import com.khalturin.dmitriy.domain.interactor.GetFeedsList
import com.khalturin.dmitriy.domain.interactor.SetFeed
import com.khalturin.dmitriy.domain.vo.Feed
import dmitriykhalturin.rssreader.RssReaderApplication
import dmitriykhalturin.rssreader.view.viewmodel.BookmarksViewModel
import dmitriykhalturin.rssreader.view.viewmodel.mapper.CardFeedMapper
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 0:59.
 */
class BookmarksPresenter : BasePresenter<BookmarksViewModel>(BookmarksViewModel::class.java) {

  @Inject lateinit var mGetFeedsList: GetFeedsList

  @Inject lateinit var mAddFeed: AddFeed

  @Inject lateinit var mSetFeed: SetFeed

  @Inject lateinit var mCardFeedMapper: CardFeedMapper

  private val mComponent by lazy { RssReaderApplication.getInjector().getsApplicationComponent() }

  init {
    mComponent.inject(this)

    addDisposables(
      mGetFeedsList.execute(null).subscribe(this::rssFeedUpdated, this::onException)
    )
  }

  override fun buildViewModel() {
    addDisposables(
      mViewModel.getOnAddRssUrl().subscribe(this::addRssUrl),
      mViewModel.getOnSetRssFeed().subscribe(this::setRssFeed)
    )
  }

  private fun addRssUrl(rssUrl: String) {
    addDisposables(
      mAddFeed.execute(rssUrl).subscribe(this::rssFeedAdded, this::onException)
    )
  }

  private fun setRssFeed(feedId: Long) {
    addDisposables(
      mSetFeed.execute(feedId).subscribe(this::rssFeedChanged, this::onException)
    )
  }

  private fun rssFeedUpdated(feeds: MutableList<Feed>) {
    val recyclerViewManager = mViewModel.mRecyclerViewManager.get()

    recyclerViewManager?.mAdapter?.setItems(mCardFeedMapper.transform(feeds, mViewModel))
  }

  private fun rssFeedAdded(success: Boolean) {
    mViewModel.mRssUrl.set(null)
  }

  private fun rssFeedChanged(success: Boolean) {
    mViewModel.finish()
  }

  private fun onException(throwable: Throwable) {

  }

}
