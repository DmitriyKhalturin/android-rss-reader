package com.khalturin.dmitriy.rssreader.state

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import com.khalturin.dmitriy.rssreader.view.activity.BookmarksActivity

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 30.10.18 23:25.
 */
class Navigate constructor(private val mContext: Context) {

  private fun startActivity(clazz: Class<*>, bundle: Bundle?, isRoot: Boolean) {
    val intent = Intent(mContext, clazz)

    val flags = if (isRoot) FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK else FLAG_ACTIVITY_NEW_TASK

    when (bundle) {
      is Bundle -> intent.putExtras(bundle)
    }

    intent.flags = flags

    mContext.startActivity(intent)
  }

  fun navigateToArticle() {}

  fun navigateToBookmarks() {
    startActivity(BookmarksActivity::class.java, null, false)
  }

}
