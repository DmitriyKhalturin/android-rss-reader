package com.khalturin.dmitriy.rssreader.presenter

import com.khalturin.dmitriy.rssreader.RssReaderApplication
import com.khalturin.dmitriy.rssreader.state.Navigate
import com.khalturin.dmitriy.rssreader.view.viewmodel.FeedViewModel
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 0:59.
 */
class FeedPresenter : BasePresenter<FeedViewModel>(FeedViewModel::class.java) {

  private val mComponent by lazy { RssReaderApplication.getInjector().getsApplicationComponent() }

  @Inject
  lateinit var mNavigate: Navigate

  init {
    mComponent.inject(this)
  }

  override fun buildViewModel() {
  }

  fun navigateToArticle() = mNavigate.navigateToArticle()

  fun navigateToBookmarks() = mNavigate.navigateToBookmarks()

}
