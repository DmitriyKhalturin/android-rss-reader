package dmitriykhalturin.rssreader.view.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.khalturin.dmitriy.library.recyclerview.RecyclerViewManager
import com.khalturin.dmitriy.library.recyclerview.adapter.BindingRecyclerAdapter
import dmitriykhalturin.rssreader.BR
import dmitriykhalturin.rssreader.R
import dmitriykhalturin.rssreader.RssReaderApplication
import dmitriykhalturin.rssreader.databinding.ActivityFeedBinding
import dmitriykhalturin.rssreader.presenter.FeedPresenter
import dmitriykhalturin.rssreader.view.viewmodel.CardArticleViewModel
import dmitriykhalturin.rssreader.view.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.activity_bookmarks.*
import javax.inject.Inject

class FeedActivity: AppCompatActivity() {

  private val mComponent by lazy { RssReaderApplication.getInjector().getPresenterComponent(this) }

  @Inject lateinit var mPresenter: FeedPresenter

  private lateinit var mBinding: ActivityFeedBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mBinding = DataBindingUtil
      .setContentView(this, R.layout.activity_feed)

    setSupportActionBar(toolbar)
    setTitle(R.string.activity_title__Feed)

    mComponent.inject(this)

    mPresenter.getLiveData().observe(this, Observer { viewModel -> setViewModel(viewModel) })
  }

  private fun setViewModel(viewModel: FeedViewModel?) {
    if(viewModel?.mRecyclerViewManager?.get() == null){
      viewModel?.mRecyclerViewManager?.set(getRecyclerViewManager())
    }

    mBinding.feedViewModel = viewModel
  }

  private fun getRecyclerViewManager() = RecyclerViewManager<CardArticleViewModel>(
    LinearLayoutManager(this),
    DefaultItemAnimator(),
    BindingRecyclerAdapter(
      R.layout.card_view_article,
      null,
      BR.cardArticle
    )
  )

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
