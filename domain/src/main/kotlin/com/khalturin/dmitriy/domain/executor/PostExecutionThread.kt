package com.khalturin.dmitriy.domain.executor

import io.reactivex.Scheduler

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.10.18 20:34.
 */
interface PostExecutionThread {

  fun getScheduler(): Scheduler

}
