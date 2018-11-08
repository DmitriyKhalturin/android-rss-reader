package com.khalturin.dmitriy.rssreader.view.viewmodel

import android.databinding.ObservableField

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 08.11.18 0:12.
 */
open class CardFeedViewModel {

  val mId = ObservableField<Long>()
  val mUrl = ObservableField<String>()
  val mLastUpdateDate = ObservableField<String>()

}
