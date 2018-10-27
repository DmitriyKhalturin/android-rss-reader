package com.khalturin.dmitriy.data.database.entity

import android.arch.persistence.room.*
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:53.
 */
@Entity(
  tableName = "article",
  foreignKeys = [
    ForeignKey(
      entity = FeedEntity::class,
      parentColumns = ["id"],
      childColumns = ["feed_id"]
    )
  ],
  indices = [
    Index("feed_id")
  ]
)
data class ArticleEntity(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val mId: Long,

  @ColumnInfo(name = "feed_id")
  val mFeedId: Long,

  @ColumnInfo(name = "title")
  val title: String,

  @ColumnInfo(name = "author")
  val author: String,

  @ColumnInfo(name = "description")
  val description: String,

  @ColumnInfo(name = "content")
  val content: String,

  @ColumnInfo(name = "image")
  val image: String,

  @ColumnInfo(name = "link")
  val link: String,

  @ColumnInfo(name = "publication_date")
  val publicationDate: Date,

  @ColumnInfo(name = "categories")
  val categories: List<String>

)
