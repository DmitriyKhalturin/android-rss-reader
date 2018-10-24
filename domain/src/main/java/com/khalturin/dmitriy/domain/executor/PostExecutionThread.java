package com.khalturin.dmitriy.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 24.10.18 20:48.
 */
public interface PostExecutionThread {

  Scheduler getScheduler();

}
