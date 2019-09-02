package dmitriykhalturin.data.repository

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import dmitriykhalturin.data.database.SQLiteDatabase
import dmitriykhalturin.data.database.entity.FeedEntity
import dmitriykhalturin.data.database.entity.SettingsEntity
import dmitriykhalturin.data.dto.mapper.ArticleDataMapper
import dmitriykhalturin.data.dto.mapper.FeedEntityMapper
import dmitriykhalturin.data.exception.FeedExistException
import dmitriykhalturin.data.exception.FeedParseException
import dmitriykhalturin.data.exception.NullableArticleListException
import com.khalturin.dmitriy.domain.repository.FeedRepository
import com.khalturin.dmitriy.domain.vo.Feed
import com.khalturin.dmitriy.library.rx.MyObserverCompose
import com.prof.rssparser.Parser
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 20:38.
 */
class FeedRepositoryImpl @Inject constructor(mContext: Context) : FeedRepository {

  private val database: SQLiteDatabase = SQLiteDatabase.getInstance(mContext) !!
  private val feedDao = database.feedDao()
  private val articleDao = database.articleDao()
  private val settingsDao = database.settingsDao()

  override fun getFeedsList() = Observable.create<MutableList<Feed>> { emitter ->
    feedDao.getObservableFeedsList()
      .map { list -> FeedEntityMapper.transform(list) }
      .subscribe(
        emitter::onNext,
        emitter::onError
      )
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun addFeed(feedUrl: String) = Observable.create<Boolean> { emitter ->
    val count = feedDao.getFeedExist(feedUrl)

    if(count == 0){
      val handlerThread = HandlerThread(Thread.currentThread().name, THREAD_PRIORITY_BACKGROUND)
      val parse = Parser()

      parse.execute(feedUrl)

      parse.onFinish(object : Parser.OnTaskCompleted {
        override fun onTaskCompleted(p0: ArrayList<com.prof.rssparser.Article>?) {
          handlerThread.start()

          val handler = Handler(handlerThread.looper)

          handler.post {
            try {
              val data = p0 ?: throw NullableArticleListException()
              val feedId = feedDao.addFeed(FeedEntity(
                null,
                feedUrl,
                Date()
              ))

              articleDao.addArticles(ArticleDataMapper.transform(data, feedId))

              settingsDao.updateSettings(SettingsEntity(
                1L,
                feedId
              ))

              emitter.onNext(true)
              emitter.onComplete()
            } catch (exception: Exception) {
              emitter.onError(exception)
            }

            handlerThread.quitSafely()
          }
        }

        override fun onError() = emitter.onError(FeedParseException())
      })
    }else{
      emitter.onError(FeedExistException())
    }
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun setFeed(feedId: Long) = Observable.create<Boolean> { emitter ->
    settingsDao.updateSettings(SettingsEntity(
      1L,
      feedId
    ))

    emitter.onNext(true)
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

}
