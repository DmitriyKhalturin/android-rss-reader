package dmitriykhalturin.rssreader.view.viewmodel.mapper

import com.khalturin.dmitriy.domain.vo.Feed
import dmitriykhalturin.rssreader.constant.Pattern.DATE_FORMAT
import dmitriykhalturin.rssreader.view.viewmodel.BookmarksViewModel
import dmitriykhalturin.rssreader.view.viewmodel.CardFeedViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 11.11.18 20:38.
 */
class CardFeedMapper @Inject constructor() {

  private fun transform(feed: Feed, bookmarks: BookmarksViewModel): CardFeedViewModel {
    val viewModel = CardFeedViewModel()

    viewModel.mId.set(feed.mId)
    viewModel.mUrl.set(feed.mUrl)

    val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    viewModel.mLastUpdateDate.set(dateFormat.format(feed.mLastUpdateDate))

    viewModel.mBookmarks = bookmarks

    return viewModel
  }

  fun transform(feeds: MutableList<Feed>, bookmarks: BookmarksViewModel): MutableList<CardFeedViewModel> {
    val list = ArrayList<CardFeedViewModel>()

    feeds.map { item -> list.add(transform(item, bookmarks)) }

    return list
  }

}
