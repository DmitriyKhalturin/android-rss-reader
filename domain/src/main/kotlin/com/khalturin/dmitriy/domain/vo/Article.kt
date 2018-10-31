package com.khalturin.dmitriy.domain.vo

import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.10.18 23:38.
 */
data class Article(
  var mId: Long,
  var mTitle: String,
  var mAuthor: String,
  var mDescription: String,
  var mContent: String,
  var mImage: String,
  var mLink: String,
  var mPublicationDate: Date,
  var mCategories: List<String>,
  var mIsWasRead: Boolean
)
