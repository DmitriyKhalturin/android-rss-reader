package com.khalturin.dmitriy.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.khalturin.dmitriy.presentation.BR;
import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.RssApplication;
import com.khalturin.dmitriy.presentation.databinding.ActivityFeedBinding;
import com.khalturin.dmitriy.presentation.presenter.FeedPresenter;
import com.khalturin.dmitriy.presentation.viewmodel.news.NewsViewModel;

import javax.inject.Inject;

import dmitriy.khalturin.com.view.recycler.binding.RecyclerManager;
import dmitriy.khalturin.com.view.recycler.binding.adapter.BindingRecyclerAdapter;

import static com.khalturin.dmitriy.presentation.view.SupportActionBar.setRootActionBar;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 20:28.
 */

public class FeedActivity extends AppCompatActivity {

//==================================================================================================
//    Class Variables
//==================================================================================================

  @Inject
  FeedPresenter mPresenter;

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityFeedBinding binding = DataBindingUtil
      .setContentView(this, R.layout.activity_feed);

    RssApplication.getInjector()
      .getPresenterComponent(this)
      .inject(this);

    bindPresenter(binding, mPresenter);
    setupPresenter(mPresenter);

    setRootActionBar(this, R.id.toolbar);
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
        mPresenter.changeRssUrlLayoutVisibility();
        break;
      case R.id.action_bookmarks:
        mPresenter.openBookmarks();
        break;
      default:
        return super.onOptionsItemSelected(item);
    }

    return true;
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  private void bindPresenter(ActivityFeedBinding binding, FeedPresenter presenter){
    presenter.getFeedObserve()
      .observe(this, binding::setFeedViewModel);
    presenter.getRssUrlObserve()
      .observe(this, binding::setRssUrlViewModel);
    presenter.getSwipeRefreshObserve()
      .observe(this, binding::setSwipeRefreshViewModel);
  }

  private void setupPresenter(FeedPresenter presenter){
    presenter.setRecyclerManager(getRecyclerManager());
    presenter.setActionsListeners();
    presenter.setRssUrlLayoutVisibility();
  }

  @SuppressWarnings("unchecked")
  private RecyclerManager getRecyclerManager(){
    RecyclerManager recyclerManager = new RecyclerManager();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    BindingRecyclerAdapter<NewsViewModel> adapter = new BindingRecyclerAdapter(R.layout.view_feed_card, null, BR.newsViewModel);

    recyclerManager.setLayoutManager(layoutManager);
    recyclerManager.setItemAnimator(itemAnimator);
    recyclerManager.setAdapter(adapter);

    return recyclerManager;
  }

}
