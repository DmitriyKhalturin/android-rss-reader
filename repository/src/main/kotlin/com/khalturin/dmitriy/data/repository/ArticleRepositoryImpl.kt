package com.khalturin.dmitriy.data.repository

import android.content.Context
import com.khalturin.dmitriy.data.database.SQLiteDatabase
import com.khalturin.dmitriy.data.dto.mapper.ArticleDataMapper
import com.khalturin.dmitriy.data.dto.mapper.ArticleEntityMapper
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
class ArticleRepositoryImpl @Inject constructor(context: Context) : ArticleRepository {

  private val database: SQLiteDatabase = SQLiteDatabase.getInstance(context)!!
  private val feedDao = database.feedDao()
  private val articleDao = database.articleDao()
  private val settingsDao = database.settingsDao()

  override fun getArticlesList() = Observable.create<List<Article>> { emitter ->
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
    val feedId = settingsDao.getFeedId()
    val feed = feedDao.getFeed(feedId)
    val feedUrl = feed.mUrl
    val parser = Parser()

    parser.execute(feedUrl)

    parser.onFinish(object : Parser.OnTaskCompleted {
      override fun onTaskCompleted(p0: ArrayList<com.prof.rssparser.Article>?) {
        try {
          val data = p0 ?: throw NullPointerException() // TODO: throw null articles exception

          feed.mLastUpdateDate = Date()

          // TODO: merge with exist articles
          articleDao.addArticles(ArticleDataMapper.transform(data, feedId))

          emitter.onNext(true)
          emitter.onComplete()
        } catch (exception: Exception) {
          emitter.onError(exception)
        }
      }

      override fun onError() = emitter.onError(NullPointerException()) // TODO: pipe for parser exception
    })
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun getArticle(articleId: Long) = Observable.create<Article> { emitter ->
    val article = articleDao.getArticle(articleId)

    article.mIsWasRead = true

    articleDao.setArticle(article)

    emitter.onNext(ArticleEntityMapper.transform(article))
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

}
