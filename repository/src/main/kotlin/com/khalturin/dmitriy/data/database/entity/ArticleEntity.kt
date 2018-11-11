package com.khalturin.dmitriy.data.database.entity

import android.arch.persistence.room.*
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:53.
 */
@Entity(
  tableName = "articles",
  foreignKeys = [
    ForeignKey(
      entity = FeedEntity::class,
      parentColumns = ["id"],
      childColumns = ["feed_id"]
    )
  ],
  indices = [
    Index(
      value = [
        "feed_id"
      ]
    )
  ]
)
data class ArticleEntity(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val mId: Long?,

  @ColumnInfo(name = "feed_id")
  val mFeedId: Long,

  @ColumnInfo(name = "title")
  val mTitle: String?,

  @ColumnInfo(name = "author")
  val mAuthor: String?,

  @ColumnInfo(name = "description")
  val mDescription: String?,

  @ColumnInfo(name = "content")
  val mContent: String?,

  @ColumnInfo(name = "image")
  val mImage: String?,

  @ColumnInfo(name = "link")
  val mLink: String?,

  @ColumnInfo(name = "publication_date")
  val mPublicationDate: Date?,

  @ColumnInfo(name = "categories")
  val mCategories: List<String>?,

  @ColumnInfo(name = "is_was_read")
  var mIsWasRead: Boolean = false

)
