package com.khalturin.dmitriy.domain.interactor

import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import com.khalturin.dmitriy.domain.repository.ArticleRepository
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 16:58.
 */
class UpdateArticlesList @Inject constructor(
  private val mRepository: ArticleRepository,
  mThreadExecutor: ThreadExecutor,
  mPostExecutionThread: PostExecutionThread
) : UseCase<Boolean, Void>(mThreadExecutor, mPostExecutionThread) {

  override fun buildUseCaseObservable(params: Void) = mRepository.updateArticlesList()

}
