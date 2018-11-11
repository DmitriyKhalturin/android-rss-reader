package com.khalturin.dmitriy.rssreader.view.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.khalturin.dmitriy.library.recyclerview.RecyclerViewManager
import com.khalturin.dmitriy.library.recyclerview.adapter.BindingRecyclerAdapter
import com.khalturin.dmitriy.rssreader.BR
import com.khalturin.dmitriy.rssreader.R
import com.khalturin.dmitriy.rssreader.RssReaderApplication
import com.khalturin.dmitriy.rssreader.databinding.ActivityBookmarksBinding
import com.khalturin.dmitriy.rssreader.presenter.BookmarksPresenter
import com.khalturin.dmitriy.rssreader.view.viewmodel.BookmarksViewModel
import com.khalturin.dmitriy.rssreader.view.viewmodel.CardFeedViewModel
import kotlinx.android.synthetic.main.activity_bookmarks.*
import javax.inject.Inject

class BookmarksActivity: AppCompatActivity() {

  private val mComponent by lazy { RssReaderApplication.getInjector().getPresenterComponent(this) }

  @Inject lateinit var mPresenter: BookmarksPresenter

  private lateinit var mBinding: ActivityBookmarksBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mBinding = DataBindingUtil
      .setContentView(this, R.layout.activity_bookmarks)

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    setTitle(R.string.activity_title__bookmarks)

    mComponent.inject(this)

    mPresenter.getLiveData().observe(this, Observer { viewModel -> setViewModel(viewModel) })
  }

  private fun setViewModel(viewModel: BookmarksViewModel?) {
    if(viewModel?.mRecyclerViewManager?.get() == null){
      viewModel?.mRecyclerViewManager?.set(getRecyclerViewManager())
    }

    mBinding.bookmarksViewModel = viewModel

    viewModel?.getOnFinish()?.subscribe { _ -> this.finish() }
  }

  private fun getRecyclerViewManager() = RecyclerViewManager<CardFeedViewModel>(
    LinearLayoutManager(this),
    DefaultItemAnimator(),
    BindingRecyclerAdapter(
      R.layout.card_view_feed,
      null,
      BR.cardFeed
    )
  )

}
