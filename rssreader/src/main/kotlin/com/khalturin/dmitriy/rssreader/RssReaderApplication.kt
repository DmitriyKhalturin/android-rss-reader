package com.khalturin.dmitriy.rssreader

import android.app.Application
import com.khalturin.dmitriy.rssreader.di.Injector

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 3:00.
 */
class RssReaderApplication : Application() {

  companion object {

    private val sInjector = Injector.getsInstance()

    fun getInjector() = sInjector

  }

  override fun onCreate() {
    super.onCreate()

    sInjector.buildAppComponent(this)
  }

}
