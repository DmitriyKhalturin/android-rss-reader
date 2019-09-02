package dmitriykhalturin.rssreader.view.viewmodel

import android.databinding.ObservableField
import com.khalturin.dmitriy.library.recyclerview.RecyclerViewManager
import io.reactivex.subjects.PublishSubject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 1:00.
 */
class FeedViewModel : CardFeedViewModel() {

  val mRecyclerViewManager = ObservableField<RecyclerViewManager<CardArticleViewModel>>()

  private val mSubjectReadArticle = PublishSubject.create<Long>()

  fun getOnReadArticle() = mSubjectReadArticle

  fun readArticle(articleId: Long) = mSubjectReadArticle.onNext(articleId)

}
