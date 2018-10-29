package com.khalturin.dmitriy.rssreader.di.module

import com.khalturin.dmitriy.data.executor.JobExecutor
import com.khalturin.dmitriy.data.repository.ArticleRepositoryImpl
import com.khalturin.dmitriy.data.repository.FeedRepositoryImpl
import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import com.khalturin.dmitriy.domain.repository.ArticleRepository
import com.khalturin.dmitriy.domain.repository.FeedRepository
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
  fun provideThreadExecutor(executor: JobExecutor): ThreadExecutor = executor

  @Singleton
  @Provides
  fun providePostExecutionThread(thread: UIThread): PostExecutionThread = thread

  @Singleton
  @Provides
  fun provideArticleRepository(repository: ArticleRepositoryImpl): ArticleRepository = repository

  @Singleton
  @Provides
  fun provideFeedRepository(repository: FeedRepositoryImpl): FeedRepository = repository

}
