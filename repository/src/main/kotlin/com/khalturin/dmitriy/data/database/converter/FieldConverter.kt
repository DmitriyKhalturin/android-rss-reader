package com.khalturin.dmitriy.data.database.converter

import android.arch.persistence.room.TypeConverter
import android.text.TextUtils
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:53.
 */
class FieldConverter {

  companion object {

    private const val LIST_ITEM_DELIMITER = ","

    @JvmStatic
    @TypeConverter
    fun millisecondsToDate(milliseconds: Long) = Date(milliseconds)

    @JvmStatic
    @TypeConverter
    fun dateToMilliseconds(date: Date) = date.time

    @JvmStatic
    @TypeConverter
    fun toStringList(string: String?) = string ?: Arrays.asList(TextUtils.split(string, LIST_ITEM_DELIMITER))

    @JvmStatic
    @TypeConverter
    fun fromStringList(list: List<String>?) = list ?: TextUtils.join(LIST_ITEM_DELIMITER, list)

  }

}
