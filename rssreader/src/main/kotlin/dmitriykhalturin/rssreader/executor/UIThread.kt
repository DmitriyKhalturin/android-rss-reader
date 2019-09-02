package dmitriykhalturin.rssreader.executor

import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.10.18 0:30.
 */
class UIThread @Inject constructor() : PostExecutionThread {

  override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()

}
