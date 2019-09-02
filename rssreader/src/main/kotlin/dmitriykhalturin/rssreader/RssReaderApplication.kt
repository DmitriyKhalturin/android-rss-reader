package dmitriykhalturin.rssreader

import android.app.Application
import dmitriykhalturin.rssreader.di.Injector

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 3:00.
 */
class RssReaderApplication : Application() {

  companion object {

    @JvmStatic
    private val sInjector = Injector.getsInstance()

    @JvmStatic
    fun getInjector() = sInjector

  }

  override fun onCreate() {
    super.onCreate()

    sInjector.buildAppComponent(this)
  }

}
