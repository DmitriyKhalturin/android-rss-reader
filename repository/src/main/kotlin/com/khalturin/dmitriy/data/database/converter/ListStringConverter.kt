package com.khalturin.dmitriy.data.database.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 29.10.18 23:13.
 */
object ListStringConverter {

  @JvmStatic
  @TypeConverter
  fun toStringList(string: String?): List<String>? {
    return when (string) {
      is String -> Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)
      else -> null
    }
  }

  @JvmStatic
  @TypeConverter
  fun fromStringList(list: List<String>?): String? {
    return when (list) {
      is List<String> -> Gson().toJson(list)
      else -> null
    }
  }

}
