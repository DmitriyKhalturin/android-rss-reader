package com.khalturin.dmitriy.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.khalturin.dmitriy.data.database.converter.DateConverter
import com.khalturin.dmitriy.data.database.converter.ListStringConverter
import com.khalturin.dmitriy.data.database.dao.ArticleDao
import com.khalturin.dmitriy.data.database.dao.FeedDao
import com.khalturin.dmitriy.data.database.dao.SettingsDao
import com.khalturin.dmitriy.data.database.entity.ArticleEntity
import com.khalturin.dmitriy.data.database.entity.FeedEntity
import com.khalturin.dmitriy.data.database.entity.SettingsEntity

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 18:05.
 */

const val DATABASE_NAME = "sqlite.db"

@Database(
  version = 1,
  exportSchema = false,
  entities = [
    FeedEntity::class,
    ArticleEntity::class,
    SettingsEntity::class
  ]
)
@TypeConverters(
  DateConverter::class,
  ListStringConverter::class
)
abstract class SQLiteDatabase : RoomDatabase() {

  companion object {

    @JvmStatic
    private var sInstance: SQLiteDatabase? = null

    @JvmStatic
    fun getInstance(context: Context): SQLiteDatabase? {
      if (sInstance == null) {
        synchronized(SQLiteDatabase::class) {
          if(sInstance == null) {
            sInstance = buildDatabase(context)
          }
        }
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
  abstract fun settingsDao(): SettingsDao

}
