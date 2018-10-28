package com.khalturin.dmitriy.domain.vo

import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.10.18 23:37.
 */
data class Feed(
  var id: Long,
  var url: String,
  var lastUpdateDate: Date
)
