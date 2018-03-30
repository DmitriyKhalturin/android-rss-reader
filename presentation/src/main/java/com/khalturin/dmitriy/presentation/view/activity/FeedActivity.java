package com.khalturin.dmitriy.presentation.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.khalturin.dmitriy.presentation.BR;
import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerManager;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;
import com.khalturin.dmitriy.presentation.databinding.ActivityFeedBinding;
import com.khalturin.dmitriy.presentation.presenter.FeedPresenter;
import com.khalturin.dmitriy.presentation.view.FeedView;
import com.khalturin.dmitriy.presentation.viewmodel.feed.FeedViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.feed.RefreshViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.feed.RssUrlViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.news.NewsViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 20:28.
 */

public class FeedActivity extends BaseActivity implements FeedView {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private FeedViewModel mFeedViewModel = new FeedViewModel();
  private RssUrlViewModel mRssUrlViewModel = new RssUrlViewModel();
  private RefreshViewModel mRefreshViewModel = new RefreshViewModel();

//==================================================================================================
//    Class Constructor
//==================================================================================================

  public FeedActivity(){
    rssPresenter = new FeedPresenter(this);
  }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityFeedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);

    // TODO: check this setter
    mFeedViewModel.recyclerManager.set(getRecyclerManager());

    binding.setFeedViewModel(mFeedViewModel);
    binding.setRssUrlViewModel(mRssUrlViewModel);
    binding.setRefreshViewModel(mRefreshViewModel);

    setRssUrlLayoutSettings();

    setSupportActionBar(findViewById(R.id.toolbar));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater inflater = getMenuInflater();

    inflater.inflate(R.menu.action_bar_menu, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    int id = item.getItemId();

    switch(id){
      case  R.id.action_add_news:
        mRssUrlViewModel.changeLayoutVisibility();
        break;
      case R.id.action_bookmarks:
        Intent intent = new Intent(this, BookmarksActivity.class);

        startActivity(intent);
        break;
      default:
        return super.onOptionsItemSelected(item);
    }

    return true;
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  @SuppressWarnings("unchecked")
  private RecyclerManager getRecyclerManager(){
    RecyclerManager recyclerManager = new RecyclerManager();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    BindingRecyclerAdapter<NewsViewModel> adapter = new BindingRecyclerAdapter(R.layout.feed_card_view, null, BR.newsViewModel);

    recyclerManager.setLayoutManager(layoutManager);
    recyclerManager.setItemAnimator(itemAnimator);
    recyclerManager.setAdapter(adapter);

    return recyclerManager;
  }

  private void setRssUrlLayoutSettings(){
    int delay = 1000;
    Handler handler = new Handler();

    handler.postDelayed(() -> {
      int size = mFeedViewModel.getFeedItemCount();

      if(size == 0){
        mRssUrlViewModel.isVisible.set(true);
      }
    }, delay);
  }

//==================================================================================================
//    Class Implementation FeedView
//==================================================================================================

  @Override
  public Observable<String> getOnUpdateRssUrl(){
    return mRssUrlViewModel.getOnUpdateRssUrl();
  }

  @Override
  public void setUpdateRssUrlComplete(){
    mRssUrlViewModel.setUpdateRssUrlComplete();
  }

  @Override
  public void setFeedItems(List<NewsViewModel> items){
    mFeedViewModel.setFeedItems(items);
  }

  @Override
  public Observable<Boolean> getOnUpdateFeedItems(){
    return mRefreshViewModel.getOnUpdateFeedItems()
      .map(aBoolean -> {
        mRssUrlViewModel.isVisible.set(false);
        return aBoolean;
      });
  }

  @Override
  public void setUpdateFeedItemsComplete(){
    mRefreshViewModel.isRefreshing.set(false);
  }

}
