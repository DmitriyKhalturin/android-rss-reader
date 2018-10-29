package com.khalturin.dmitriy.data.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:53.
 */
object DateConverter {

  @JvmStatic
  @TypeConverter
  fun millisecondsToDate(milliseconds: Long?) = when (milliseconds) {
    is Long -> Date(milliseconds)
    else -> null
  }

  @JvmStatic
  @TypeConverter
  fun dateToMilliseconds(date: Date?) = date?.time

}
