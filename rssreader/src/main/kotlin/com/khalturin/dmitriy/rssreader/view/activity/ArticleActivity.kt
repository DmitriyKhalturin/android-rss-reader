package com.khalturin.dmitriy.rssreader.view.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.khalturin.dmitriy.rssreader.R
import com.khalturin.dmitriy.rssreader.RssReaderApplication
import com.khalturin.dmitriy.rssreader.databinding.ActivityArticleBinding
import com.khalturin.dmitriy.rssreader.presenter.ArticlePresenter
import com.khalturin.dmitriy.rssreader.presenter.ArticlePresenter.Companion.UNKNOWN_ARTICLE_ID

import kotlinx.android.synthetic.main.activity_article.*
import javax.inject.Inject

class ArticleActivity: AppCompatActivity() {

  companion object {

    const val ARTICLE_ID_FIELD = "ARTICLE_ID_FIELD"

  }

  private val mComponent by lazy { RssReaderApplication.getInjector().getPresenterComponent(this) }

  @Inject lateinit var mPresenter: ArticlePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: ActivityArticleBinding = DataBindingUtil
      .setContentView(this, R.layout.activity_article)

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    mComponent.inject(this)

    mPresenter.setArticleId(intent?.extras?.getLong(ARTICLE_ID_FIELD) ?: UNKNOWN_ARTICLE_ID)

    mPresenter.getLiveData().observe(this, Observer { article ->
      binding.articleViewModel = article
    })
  }

}
