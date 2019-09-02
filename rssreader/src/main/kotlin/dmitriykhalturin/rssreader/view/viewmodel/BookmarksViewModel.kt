package dmitriykhalturin.rssreader.view.viewmodel

import android.databinding.ObservableField
import android.view.View
import com.khalturin.dmitriy.library.recyclerview.RecyclerViewManager
import io.reactivex.subjects.PublishSubject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 1:00.
 */
class BookmarksViewModel {

  val mRecyclerViewManager = ObservableField<RecyclerViewManager<CardFeedViewModel>>()

  val mRssUrl = ObservableField<String>()

  private val mSubjectAddRssUrl = PublishSubject.create<String>()

  fun getOnAddRssUrl() = mSubjectAddRssUrl

  fun addRssUrl(view: View) {
    val rssUrl = mRssUrl.get()

    if(rssUrl is String && rssUrl.isNotBlank()) {
      mSubjectAddRssUrl.onNext(rssUrl)
    }
  }

  private val mSubjectSetRssFeed = PublishSubject.create<Long>()

  fun getOnSetRssFeed() = mSubjectSetRssFeed

  fun setRssFeed(feedId: Long) = mSubjectSetRssFeed.onNext(feedId)

  private val mSubjectFinish = PublishSubject.create<Boolean>()

  fun getOnFinish() = mSubjectFinish

  fun finish() = mSubjectFinish.onNext(true)

}
