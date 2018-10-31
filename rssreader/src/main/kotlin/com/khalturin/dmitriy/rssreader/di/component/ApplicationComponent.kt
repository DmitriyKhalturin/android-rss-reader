package com.khalturin.dmitriy.rssreader.di.component

import com.khalturin.dmitriy.rssreader.di.module.ApplicationModule
import com.khalturin.dmitriy.rssreader.di.module.RepositoryModule
import com.khalturin.dmitriy.rssreader.di.module.StateModule
import com.khalturin.dmitriy.rssreader.presenter.ArticlePresenter
import com.khalturin.dmitriy.rssreader.presenter.BookmarksPresenter
import com.khalturin.dmitriy.rssreader.presenter.FeedPresenter
import com.khalturin.dmitriy.rssreader.view.activity.ArticleActivity
import com.khalturin.dmitriy.rssreader.view.activity.BookmarksActivity
import com.khalturin.dmitriy.rssreader.view.activity.FeedActivity
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
