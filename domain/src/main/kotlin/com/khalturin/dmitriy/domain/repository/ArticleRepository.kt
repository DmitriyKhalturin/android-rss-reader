package com.khalturin.dmitriy.domain.repository

import com.khalturin.dmitriy.domain.vo.Article
import io.reactivex.Observable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 18:05.
 */
interface ArticleRepository {

  fun getArticlesList(): Observable<MutableList<Article>>
  fun updateArticlesList(): Observable<Boolean>
  fun getArticle(articleId: Long): Observable<Article>

}
