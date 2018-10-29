package com.khalturin.dmitriy.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.khalturin.dmitriy.data.database.entity.SettingsEntity
import io.reactivex.Flowable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 29.10.18 0:11.
 */
@Dao
interface SettingsDao {

  @Query("SELECT feed_id FROM settings WHERE id = 1")
  fun getFeedId(): Long

  @Query("SELECT feed_id FROM settings WHERE id = 1")
  fun getObservableFeedId(): Flowable<Long>

  @Insert(onConflict = REPLACE)
  fun updateSettings(settings: SettingsEntity)

}
