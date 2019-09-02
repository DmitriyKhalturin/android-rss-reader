package dmitriykhalturin.data.repository

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Process
import dmitriykhalturin.data.database.SQLiteDatabase
import dmitriykhalturin.data.dto.mapper.ArticleDataMapper
import dmitriykhalturin.data.dto.mapper.ArticleEntityMapper
import dmitriykhalturin.data.exception.FeedParseException
import dmitriykhalturin.data.exception.NullableArticleListException
import com.khalturin.dmitriy.domain.repository.ArticleRepository
import com.khalturin.dmitriy.domain.vo.Article
import com.khalturin.dmitriy.library.rx.MyObserverCompose
import com.prof.rssparser.Parser
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 20:39.
 */
class ArticleRepositoryImpl @Inject constructor(mContext: Context) : ArticleRepository {

  private val database: SQLiteDatabase = SQLiteDatabase.getInstance(mContext) !!
  private val feedDao = database.feedDao()
  private val articleDao = database.articleDao()
  private val settingsDao = database.settingsDao()

  override fun getArticlesList() = Observable.create<MutableList<Article>> { emitter ->
    settingsDao.getObservableFeedId()
      .subscribe(
        { feedId ->
          articleDao.getObservableArticles(feedId)
            .map { list -> ArticleEntityMapper.transform(list) }
            .subscribe(
              emitter::onNext,
              emitter::onError
            )
        },
        emitter::onError
      )
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun updateArticlesList() = Observable.create<Boolean> { emitter ->
    val handlerThread = HandlerThread(Thread.currentThread().name, Process.THREAD_PRIORITY_BACKGROUND)
    val feedId = settingsDao.getFeedId()
    val feed = feedDao.getFeed(feedId)
    val feedUrl = feed.mUrl
    val parser = Parser()

    parser.execute(feedUrl)

    parser.onFinish(object : Parser.OnTaskCompleted {
      override fun onTaskCompleted(p0: ArrayList<com.prof.rssparser.Article>?) {
        handlerThread.start()

        val handler = Handler(handlerThread.looper)

        handler.post {
          try {
            val data = p0 ?: throw NullableArticleListException()

            feed.mLastUpdateDate = Date()

            // TODO: merge with exist articles
            articleDao.addArticles(ArticleDataMapper.transform(data, feedId))

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
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun getArticle(articleId: Long) = Observable.create<Article> { emitter ->
    val article = articleDao.getArticle(articleId)

    article.mIsWasRead = true

    articleDao.setArticle(article)

    emitter.onNext(ArticleEntityMapper.transform(article))
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

}
