package com.khalturin.dmitriy.rssreader.view.viewmodel

import android.databinding.ObservableField

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 08.11.18 0:11.
 */
open class CardArticleViewModel {

  val mId = ObservableField<Long>()
  val mTitle = ObservableField<String>()
  val mAuthor = ObservableField<String>()
  val mDescription = ObservableField<String>()
  val mContent = ObservableField<String>()
  val mImage = ObservableField<String>()
  val mLink = ObservableField<String>()
  val mPublicationDate = ObservableField<String>()
  val mIsWasRead = ObservableField<Boolean>()

  lateinit var mFeed: FeedViewModel

}
