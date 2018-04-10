package com.khalturin.dmitriy.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khalturin.dmitriy.presentation.BR;
import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.RssApplication;
import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerManager;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;
import com.khalturin.dmitriy.presentation.databinding.ActivityBookmarksBinding;
import com.khalturin.dmitriy.presentation.di.PerPresenter;
import com.khalturin.dmitriy.presentation.presenter.BookmarksPresenter;
import com.khalturin.dmitriy.presentation.viewmodel.bookmark.FeedViewModel;

import javax.inject.Inject;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 22:00.
 */

public class BookmarksActivity extends AppCompatActivity {

//==================================================================================================
//    Class Variables
//==================================================================================================

  @PerPresenter
  @Inject
  protected BookmarksPresenter mPresenter;

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityBookmarksBinding binding = DataBindingUtil
      .setContentView(this, R.layout.activity_bookmarks);

    RssApplication.getInjector().getPresenterComponent().inject(this);

    bindPresenter(binding, mPresenter);
    setupPresenter(mPresenter);

    // TODO: remove this boilerplate later
    setSupportActionBar(findViewById(R.id.toolbar));
    ActionBar actionBar = getSupportActionBar();
    if(actionBar != null){
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  protected void onDestroy() {
    RssApplication.getInjector().clearPresenterComponent();

    super.onDestroy();
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  private void bindPresenter(ActivityBookmarksBinding binding, BookmarksPresenter presenter){
    presenter.getBookmarksObserve()
      .observe(this, binding::setBookmarksViewModel);
  }

  public void setupPresenter(BookmarksPresenter presenter){
    presenter.setRecyclerManager(getRecyclerManager());
    presenter.setActionsListeners();
  }

  @SuppressWarnings("unchecked")
  private RecyclerManager getRecyclerManager(){
    RecyclerManager recyclerManager = new RecyclerManager();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    BindingRecyclerAdapter<FeedViewModel> adapter =
      new BindingRecyclerAdapter(R.layout.bookmark_card_view, null, BR.feedViewModel);

    recyclerManager.setLayoutManager(layoutManager);
    recyclerManager.setItemAnimator(itemAnimator);
    recyclerManager.setAdapter(adapter);

    return recyclerManager;
  }

}
