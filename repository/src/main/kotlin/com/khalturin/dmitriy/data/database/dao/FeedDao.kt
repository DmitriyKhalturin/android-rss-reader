package com.khalturin.dmitriy.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.khalturin.dmitriy.data.database.entity.FeedEntity
import io.reactivex.Flowable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:50.
 */
@Dao
interface FeedDao {

  @Query("SELECT * FROM feeds")
  fun getObservableFeedsList(): Flowable<List<FeedEntity>>

  @Query("SELECT * FROM feeds WHERE id = :feedId")
  fun getFeed(feedId: Long): FeedEntity

  @Insert
  fun addFeed(feed: FeedEntity): Long

}
