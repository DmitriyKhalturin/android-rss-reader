package com.khalturin.dmitriy.rssreader.view.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.khalturin.dmitriy.rssreader.R
import com.khalturin.dmitriy.rssreader.RssReaderApplication
import com.khalturin.dmitriy.rssreader.databinding.ActivityFeedBinding
import com.khalturin.dmitriy.rssreader.presenter.FeedPresenter
import kotlinx.android.synthetic.main.activity_bookmarks.*
import javax.inject.Inject

class FeedActivity: AppCompatActivity() {

  private val mComponent by lazy { RssReaderApplication.getInjector().getPresenterComponent(this) }

  @Inject
  lateinit var mPresenter: FeedPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: ActivityFeedBinding = DataBindingUtil
      .setContentView(this, R.layout.activity_feed)

    setSupportActionBar(toolbar)

    mComponent.inject(this)

    mPresenter.getLiveData().observe(this, Observer { feed ->
      binding.feedViewModel = feed
    })
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_feed, menu)

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.itemId) {
      R.id.action_bookmarks -> {
        mPresenter.navigateToBookmarks()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}
