package com.khalturin.dmitriy.domain.interactor

import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import com.khalturin.dmitriy.domain.repository.FeedRepository
import com.khalturin.dmitriy.domain.vo.Feed
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 16:41.
 */
class GetFeedsList @Inject constructor(
  private val mRepository: FeedRepository,
  mThreadExecutor: ThreadExecutor,
  mPostExecutionThread: PostExecutionThread
) : UseCase<List<Feed>, Void>(mThreadExecutor, mPostExecutionThread) {

  override fun buildUseCaseObservable(params: Void) = mRepository.getFeedsList()

}
