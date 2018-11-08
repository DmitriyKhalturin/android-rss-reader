package com.khalturin.dmitriy.data.dto.mapper

import com.khalturin.dmitriy.data.database.entity.FeedEntity
import com.khalturin.dmitriy.domain.vo.Feed

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 23:30.
 */
class FeedEntityMapper {

  companion object {

    private fun transform(entity: FeedEntity) = Feed(
      entity.mId !!,
      entity.mUrl,
      entity.mLastUpdateDate
    )

    fun transform(entities: List<FeedEntity>): List<Feed> {
      val list = ArrayList<Feed>()

      entities.map { item -> list.add(transform(item)) }

      return list
    }

  }

}