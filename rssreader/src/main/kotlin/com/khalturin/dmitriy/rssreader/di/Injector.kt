package com.khalturin.dmitriy.rssreader.di

import android.app.Application
import android.support.v4.app.FragmentActivity
import com.khalturin.dmitriy.rssreader.di.component.ApplicationComponent
import com.khalturin.dmitriy.rssreader.di.component.DaggerApplicationComponent
import com.khalturin.dmitriy.rssreader.di.component.DaggerPresenterComponent
import com.khalturin.dmitriy.rssreader.di.module.ApplicationModule
import com.khalturin.dmitriy.rssreader.di.module.PresenterModule
import com.khalturin.dmitriy.rssreader.di.module.RepositoryModule

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86></dmitry.halturin.86>@gmail.com>
 * for saferide-android on 06.08.18 15:31.
 */
class Injector {

  companion object {

    @JvmStatic
    private val sInstance = Injector()

    @JvmStatic
    private lateinit var sAppComponent: ApplicationComponent

    @JvmStatic
    fun getsInstance() = sInstance

  }

  fun buildAppComponent(application: Application) {
    sAppComponent = DaggerApplicationComponent
      .builder()
      .applicationModule(ApplicationModule(application))
      .repositoryModule(RepositoryModule())
      .build()
  }

  fun getsApplicationComponent() = sAppComponent

  fun getPresenterComponent(activity: FragmentActivity) = DaggerPresenterComponent
    .builder()
    .presenterModule(PresenterModule(activity))
    .build()

}
