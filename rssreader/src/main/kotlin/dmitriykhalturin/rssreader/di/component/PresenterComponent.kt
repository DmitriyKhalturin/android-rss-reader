package dmitriykhalturin.rssreader.di.component

import dmitriykhalturin.rssreader.di.PerPresenter
import dmitriykhalturin.rssreader.di.module.PresenterModule
import dmitriykhalturin.rssreader.view.activity.ArticleActivity
import dmitriykhalturin.rssreader.view.activity.BookmarksActivity
import dmitriykhalturin.rssreader.view.activity.FeedActivity
import dagger.Component

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:45.
 */
@PerPresenter
@Component(
  modules = [
    PresenterModule::class
  ]
)
interface PresenterComponent {

  fun inject(activity: FeedActivity)
  fun inject(activity: ArticleActivity)
  fun inject(activity: BookmarksActivity)

}
