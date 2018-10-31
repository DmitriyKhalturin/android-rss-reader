package com.khalturin.dmitriy.rssreader.di.module

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.khalturin.dmitriy.rssreader.di.PerPresenter
import com.khalturin.dmitriy.rssreader.presenter.ArticlePresenter
import com.khalturin.dmitriy.rssreader.presenter.BookmarksPresenter
import com.khalturin.dmitriy.rssreader.presenter.FeedPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:35.
 */
@Module
class PresenterModule constructor(private val mActivity: FragmentActivity) {

  @PerPresenter
  @Provides
  fun provideFeedPresenter(): FeedPresenter = ViewModelProviders
    .of(mActivity)
    .get(FeedPresenter::class.java)

  @PerPresenter
  @Provides
  fun provideArticlePresenter(): ArticlePresenter = ViewModelProviders
    .of(mActivity)
    .get(ArticlePresenter::class.java)

  @PerPresenter
  @Provides
  fun provideBookmarksPresenter(): BookmarksPresenter = ViewModelProviders
    .of(mActivity)
    .get(BookmarksPresenter::class.java)

}
