package com.khalturin.dmitriy.rssreader.view.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.khalturin.dmitriy.rssreader.R
import com.khalturin.dmitriy.rssreader.RssReaderApplication
import com.khalturin.dmitriy.rssreader.databinding.ActivityBookmarksBinding
import com.khalturin.dmitriy.rssreader.presenter.BookmarksPresenter
import kotlinx.android.synthetic.main.activity_bookmarks.*
import javax.inject.Inject

class BookmarksActivity: AppCompatActivity() {

  private val mComponent by lazy { RssReaderApplication.getInjector().getPresenterComponent(this) }

  @Inject
  lateinit var mPresenter: BookmarksPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: ActivityBookmarksBinding = DataBindingUtil
      .setContentView(this, R.layout.activity_bookmarks)

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    mComponent.inject(this)

    mPresenter.getLiveData().observe(this, Observer { bookmarks ->
      binding.bookmarksViewModel = bookmarks
    })
  }

}
