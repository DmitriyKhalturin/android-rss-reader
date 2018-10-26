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

const val CORE_POOL_SIZE = 3
const val MAX_POOL_SIZE = 5
const val KEEP_ALIVE_TIME = 10L
const val THREAD_NAME_PREFIX = "job_executor_"

class JobExecutor @Inject constructor() : ThreadExecutor {

  private val mThreadPoolExecutor = ThreadPoolExecutor(
    CORE_POOL_SIZE,
    MAX_POOL_SIZE,
    KEEP_ALIVE_TIME,
    TimeUnit.SECONDS,
    LinkedBlockingDeque(),
    JobThreadFactory()
  )

  override fun execute(command: Runnable) = mThreadPoolExecutor.execute(command)

  private class JobThreadFactory : ThreadFactory {

    override fun newThread(r: Runnable) = Thread(r, getThreadName())

    private var mCounter = 0

    fun getThreadName() = (THREAD_NAME_PREFIX + mCounter++)

  }

}
