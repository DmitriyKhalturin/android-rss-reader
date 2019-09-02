package dmitriykhalturin.rssreader.di

import android.app.Application
import android.support.v4.app.FragmentActivity
import dmitriykhalturin.rssreader.di.component.ApplicationComponent
import dmitriykhalturin.rssreader.di.component.DaggerApplicationComponent
import dmitriykhalturin.rssreader.di.component.DaggerPresenterComponent
import dmitriykhalturin.rssreader.di.module.ApplicationModule
import dmitriykhalturin.rssreader.di.module.PresenterModule
import dmitriykhalturin.rssreader.di.module.RepositoryModule
import dmitriykhalturin.rssreader.di.module.StateModule

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
      .stateModule(StateModule())
      .build() !!
  }

  fun getsApplicationComponent() = sAppComponent

  fun getPresenterComponent(activity: FragmentActivity) = DaggerPresenterComponent
    .builder()
    .presenterModule(PresenterModule(activity))
    .build() !!

}
