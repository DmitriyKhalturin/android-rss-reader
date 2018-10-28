package com.khalturin.dmitriy.domain.repository

import com.khalturin.dmitriy.domain.vo.Feed
import io.reactivex.Observable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 18:05.
 */
interface FeedRepository {

  fun getFeedsList(): Observable<List<Feed>>
  fun addFeed(feedUrl: String): Observable<Boolean>
  fun setFeed(feedId: Long): Observable<Boolean>

}
