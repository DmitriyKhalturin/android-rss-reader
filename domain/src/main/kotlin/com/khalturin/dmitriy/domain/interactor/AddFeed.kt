package com.khalturin.dmitriy.domain.interactor

import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import com.khalturin.dmitriy.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 16:57.
 */
class AddFeed @Inject constructor(
  private val mRepository: FeedRepository,
  mThreadExecutor: ThreadExecutor,
  mPostExecutionThread: PostExecutionThread
) : UseCase<Boolean, String>(mThreadExecutor, mPostExecutionThread) {

  override fun buildUseCaseObservable(params: String) = mRepository.addFeed(params)

}
