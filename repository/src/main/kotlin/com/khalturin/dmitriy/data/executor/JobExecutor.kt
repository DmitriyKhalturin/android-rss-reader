package com.khalturin.dmitriy.data.executor

import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 0:25.
 */
class JobExecutor @Inject constructor() : ThreadExecutor {

  companion object {

    private const val CORE_POOL_SIZE = 5
    private const val MAX_POOL_SIZE = 10
    private const val KEEP_ALIVE_TIME = 10L
    private const val THREAD_NAME_PREFIX = "job_executor_"

  }

  private val mThreadPoolExecutor = ThreadPoolExecutor(
    CORE_POOL_SIZE,
    MAX_POOL_SIZE,
    KEEP_ALIVE_TIME,
    TimeUnit.SECONDS,
    LinkedBlockingDeque(),
    JobThreadFactory()
  )

  override fun execute(runnable: Runnable) = mThreadPoolExecutor.execute(runnable)

  private class JobThreadFactory : ThreadFactory {

    override fun newThread(runnable: Runnable) = Thread(runnable, getThreadName())

    private var mCounter = 0

    fun getThreadName() = (THREAD_NAME_PREFIX + mCounter++)

  }

}
