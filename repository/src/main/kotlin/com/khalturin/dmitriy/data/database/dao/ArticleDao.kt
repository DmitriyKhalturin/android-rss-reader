package com.khalturin.dmitriy.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.khalturin.dmitriy.data.database.entity.ArticleEntity
import io.reactivex.Flowable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 27.10.18 21:50.
 */
@Dao
interface ArticleDao {

  @Query("SELECT * FROM articles WHERE id = :articleId")
  fun getArticle(articleId: Long): ArticleEntity

  @Insert(onConflict = REPLACE)
  fun setArticle(article: ArticleEntity)

  @Query("SELECT * FROM articles WHERE feed_id = :feedId")
  fun getObservableArticles(feedId: Long): Flowable<List<ArticleEntity>>

  @Insert(onConflict = REPLACE)
  fun addArticles(articles: List<ArticleEntity>)

}
