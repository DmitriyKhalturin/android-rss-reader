package com.khalturin.dmitriy.rssreader.state

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import com.khalturin.dmitriy.rssreader.view.activity.ArticleActivity
import com.khalturin.dmitriy.rssreader.view.activity.ArticleActivity.Companion.ARTICLE_ID_FIELD
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

  fun navigateToArticle(articleId: Long) {
    val bundle = Bundle()

    bundle.putLong(ARTICLE_ID_FIELD, articleId)

    startActivity(ArticleActivity::class.java, bundle, false)
  }

  fun navigateToBookmarks() {
    startActivity(BookmarksActivity::class.java, null, false)
  }

  fun openInBrowser(url: String) {
    val intent = Intent(ACTION_VIEW, Uri.parse(url))

    intent.flags = FLAG_ACTIVITY_NEW_TASK

    mContext.startActivity(intent)
  }

}
