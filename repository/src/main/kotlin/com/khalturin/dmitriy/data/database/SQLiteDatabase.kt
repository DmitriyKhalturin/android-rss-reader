package com.khalturin.dmitriy.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.khalturin.dmitriy.data.database.converter.FieldConverter
import com.khalturin.dmitriy.data.database.dao.ArticleDao
import com.khalturin.dmitriy.data.database.dao.FeedDao
import com.khalturin.dmitriy.data.database.entity.ArticleEntity
import com.khalturin.dmitriy.data.database.entity.FeedEntity

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 18:05.
 */

const val DATABASE_NAME = "sqlite.db"

@Database(
  version = 0,
  exportSchema = false,
  entities = [
    FeedEntity::class,
    ArticleEntity::class
  ]
)
@TypeConverters(
  FieldConverter::class
)
abstract class SQLiteDatabase : RoomDatabase() {

  companion object {

    @JvmStatic
    private var sInstance: SQLiteDatabase? = null

    @JvmStatic
    fun getInstance(context: Context): SQLiteDatabase? {
      if (sInstance == null) {
        synchronized(SQLiteDatabase::class, fun() = sInstance ?: buildDatabase(context))
      }

      return sInstance
    }

    @JvmStatic
    private fun buildDatabase(context: Context) = Room
      .databaseBuilder(context, SQLiteDatabase::class.java, DATABASE_NAME)
      .build()

  }

  abstract fun feedDao(): FeedDao
  abstract fun articleDao(): ArticleDao

}
