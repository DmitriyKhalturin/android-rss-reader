package dmitriykhalturin.rssreader.di.module

import dmitriykhalturin.data.executor.JobExecutor
import dmitriykhalturin.data.repository.ArticleRepositoryImpl
import dmitriykhalturin.data.repository.FeedRepositoryImpl
import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import com.khalturin.dmitriy.domain.repository.ArticleRepository
import com.khalturin.dmitriy.domain.repository.FeedRepository
import dmitriykhalturin.rssreader.executor.UIThread
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
