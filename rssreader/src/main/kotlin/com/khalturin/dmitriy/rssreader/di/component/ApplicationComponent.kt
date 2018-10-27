package com.khalturin.dmitriy.rssreader.di.component

import com.khalturin.dmitriy.rssreader.di.module.ApplicationModule
import com.khalturin.dmitriy.rssreader.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:45.
 */
@Singleton
@Component(
  modules = [
    ApplicationModule::class,
    RepositoryModule::class
  ]
)
interface ApplicationComponent
