package com.khalturin.dmitriy.data.dto.mapper

import com.khalturin.dmitriy.data.database.entity.ArticleEntity
import com.khalturin.dmitriy.domain.vo.Article

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 30.10.18 0:30.
 */
class ArticleEntityMapper {

  companion object {

    fun transform(entity: ArticleEntity) = Article(
      entity.mId !!,
      entity.mTitle,
      entity.mAuthor,
      entity.mDescription,
      entity.mContent,
      entity.mImage,
      entity.mLink,
      entity.mPublicationDate,
      entity.mCategories,
      entity.mIsWasRead
    )

    fun transform(entities: MutableList<ArticleEntity>): MutableList<Article> {
      val list = ArrayList<Article>()

      entities.map { item -> list.add(transform(item)) }

      return list
    }

  }

}
