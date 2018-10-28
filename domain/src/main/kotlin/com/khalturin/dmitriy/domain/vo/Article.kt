package com.khalturin.dmitriy.domain.vo

import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.10.18 23:38.
 */
data class Article(
  var id: Long,
  var title: String,
  var author: String,
  var description: String,
  var content: String,
  var image: String,
  var link: String,
  var publicationDate: Date,
  var categories: List<String>,
  var isWasRead: Boolean
)
