package com.khalturin.dmitriy.rssreader.view.viewmodel

import io.reactivex.subjects.PublishSubject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 1:00.
 */
class ArticleViewModel : CardArticleViewModel() {

  private val mSubjectOnOpenLink = PublishSubject.create<String>()

  fun getOnOpenLink() = mSubjectOnOpenLink

  fun openLink(){
    val link = mLink.get()

    if(link is String) {
      mSubjectOnOpenLink.onNext(link)
    }
  }

}
