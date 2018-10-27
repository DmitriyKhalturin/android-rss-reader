package com.khalturin.dmitriy.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:53.
 */
@Entity(
  tableName = "feed"
)
data class FeedEntity(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val mId: Long,

  @ColumnInfo(name = "url")
  val mUrl: String

)
