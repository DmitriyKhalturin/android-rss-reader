package com.khalturin.dmitriy.rssreader.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:38.
 */
@Module
class ApplicationModule(private val mApplication: Application) {

  @Singleton
  @Provides
  fun provideContext(): Context = mApplication

  @Singleton
  @Provides
  fun provideResources(): Resources = mApplication.resources

}
