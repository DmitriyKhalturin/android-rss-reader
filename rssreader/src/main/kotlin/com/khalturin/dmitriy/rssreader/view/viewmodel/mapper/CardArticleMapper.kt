package com.khalturin.dmitriy.rssreader.view.viewmodel.mapper

import com.khalturin.dmitriy.domain.vo.Article
import com.khalturin.dmitriy.rssreader.constant.Pattern
import com.khalturin.dmitriy.rssreader.view.viewmodel.CardArticleViewModel
import com.khalturin.dmitriy.rssreader.view.viewmodel.FeedViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 11.11.18 22:18.
 */
class CardArticleMapper @Inject constructor() {

  private fun transform(article: Article, feed: FeedViewModel): CardArticleViewModel {
    val viewModel = CardArticleViewModel()

    val dateFormat = SimpleDateFormat(Pattern.DATE_FORMAT, Locale.getDefault())

    viewModel.mId.set(article.mId)
    viewModel.mTitle.set(article.mTitle)
    viewModel.mAuthor.set(article.mAuthor)
    viewModel.mDescription.set(article.mDescription)
    viewModel.mContent.set(article.mContent)
    viewModel.mImage.set(article.mImage)
    viewModel.mLink.set(article.mLink)
    viewModel.mPublicationDate.set(dateFormat.format(article.mPublicationDate))
    viewModel.mIsWasRead.set(article.mIsWasRead)

    viewModel.mFeed = feed

    return viewModel
  }

  fun transform(articles: MutableList<Article>, feed: FeedViewModel): MutableList<CardArticleViewModel> {
    val list = ArrayList<CardArticleViewModel>()

    articles.map { item -> list.add(transform(item, feed)) }

    return list
  }

}
