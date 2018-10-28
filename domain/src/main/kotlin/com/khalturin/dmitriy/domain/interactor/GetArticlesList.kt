package com.khalturin.dmitriy.domain.interactor

import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import com.khalturin.dmitriy.domain.repository.ArticleRepository
import com.khalturin.dmitriy.domain.vo.Article
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 16:57.
 */
class GetArticlesList @Inject constructor(
  private val mRepository: ArticleRepository,
  mThreadExecutor: ThreadExecutor,
  mPostExecutionThread: PostExecutionThread
) : UseCase<List<Article>, Long>(mThreadExecutor, mPostExecutionThread) {

  override fun buildUseCaseObservable(params: Long) = mRepository.getArticlesList(params)

}
