package com.khalturin.dmitriy.rssreader.presenter

import com.khalturin.dmitriy.rssreader.RssReaderApplication
import com.khalturin.dmitriy.rssreader.view.viewmodel.ArticleViewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 0:59.
 */
class ArticlePresenter : BasePresenter<ArticleViewModel>(ArticleViewModel::class.java) {

  private val mComponent by lazy { RssReaderApplication.getInjector().getsApplicationComponent() }

  init {
    mComponent.inject(this)
  }

  override fun buildViewModel() {
  }

}
