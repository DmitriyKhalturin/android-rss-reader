package com.khalturin.dmitriy.rssreader.di.module

import com.khalturin.dmitriy.data.executor.JobExecutor
import com.khalturin.dmitriy.rssreader.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:34.
 */
@Module
class RepositoryModule {

  @Singleton
  @Provides
  fun provideThreadExecutor(executor: JobExecutor) = executor

  @Singleton
  @Provides
  fun providePostExecutionThread(thread: UIThread) = thread

}
