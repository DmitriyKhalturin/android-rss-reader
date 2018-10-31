package com.khalturin.dmitriy.rssreader.di.module

import android.content.Context
import com.khalturin.dmitriy.rssreader.state.Navigate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 30.10.18 23:45.
 */
@Module
class StateModule {

  @Singleton
  @Provides
  fun provideNavigate(context: Context): Navigate = Navigate(context)

}
