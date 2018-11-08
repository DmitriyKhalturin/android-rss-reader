package com.khalturin.dmitriy.rssreader.view.viewmodel

import android.databinding.ObservableField
import android.view.View
import com.khalturin.dmitriy.library.recyclerview.RecyclerViewManager

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 1:00.
 */
class BookmarksViewModel {

  val mRecyclerViewManager = ObservableField<RecyclerViewManager>()

  val mRssUrl = ObservableField<String>()

  fun loadRssFeed(view: View) {}

}
