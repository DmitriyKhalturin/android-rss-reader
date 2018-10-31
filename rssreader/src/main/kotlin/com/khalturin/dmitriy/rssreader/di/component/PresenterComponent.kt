package com.khalturin.dmitriy.rssreader.di.component

import com.khalturin.dmitriy.rssreader.di.PerPresenter
import com.khalturin.dmitriy.rssreader.di.module.PresenterModule
import com.khalturin.dmitriy.rssreader.view.activity.ArticleActivity
import com.khalturin.dmitriy.rssreader.view.activity.BookmarksActivity
import com.khalturin.dmitriy.rssreader.view.activity.FeedActivity
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
