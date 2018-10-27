package com.khalturin.dmitriy.rssreader.di.component

import com.khalturin.dmitriy.rssreader.di.PerPresenter
import com.khalturin.dmitriy.rssreader.di.module.PresenterModule
import dagger.Component

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 21:45.
 */
@PerPresenter
@Component(
  modules = [
    PresenterModule::class
  ]
)
interface PresenterComponent
