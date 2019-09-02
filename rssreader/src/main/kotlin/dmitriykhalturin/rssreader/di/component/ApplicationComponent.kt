package dmitriykhalturin.rssreader.di.component

import dmitriykhalturin.rssreader.di.module.ApplicationModule
import dmitriykhalturin.rssreader.di.module.RepositoryModule
import dmitriykhalturin.rssreader.di.module.StateModule
import dmitriykhalturin.rssreader.presenter.ArticlePresenter
import dmitriykhalturin.rssreader.presenter.BookmarksPresenter
import dmitriykhalturin.rssreader.presenter.FeedPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:45.
 */
@Singleton
@Component(
  modules = [
    ApplicationModule::class,
    RepositoryModule::class,
    StateModule::class
  ]
)
interface ApplicationComponent {

  fun inject(presenter: FeedPresenter)
  fun inject(presenter: ArticlePresenter)
  fun inject(presenter: BookmarksPresenter)

}
