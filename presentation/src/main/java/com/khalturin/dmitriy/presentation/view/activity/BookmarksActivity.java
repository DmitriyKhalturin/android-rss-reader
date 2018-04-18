package com.khalturin.dmitriy.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khalturin.dmitriy.presentation.BR;
import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.RssApplication;
import com.khalturin.dmitriy.presentation.databinding.ActivityBookmarksBinding;
import com.khalturin.dmitriy.presentation.presenter.BookmarksPresenter;
import com.khalturin.dmitriy.presentation.viewmodel.bookmark.FeedViewModel;

import javax.inject.Inject;

import dmitriy.khalturin.com.view.recycler.binding.RecyclerManager;
import dmitriy.khalturin.com.view.recycler.binding.adapter.BindingRecyclerAdapter;

import static com.khalturin.dmitriy.presentation.view.SupportActionBar.setChildActionBar;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 22:00.
 */

public class BookmarksActivity extends AppCompatActivity {

//==================================================================================================
//    Class Variables
//==================================================================================================

  @Inject
  BookmarksPresenter mPresenter;

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityBookmarksBinding binding = DataBindingUtil
      .setContentView(this, R.layout.activity_bookmarks);

    RssApplication.getInjector()
      .getPresenterComponent(this)
      .inject(this);

    bindPresenter(binding, mPresenter);
    setupPresenter(mPresenter);

    setChildActionBar(this, R.id.toolbar);
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
      new BindingRecyclerAdapter(R.layout.view_bookmark_card, null, BR.feedViewModel);

    recyclerManager.setLayoutManager(layoutManager);
    recyclerManager.setItemAnimator(itemAnimator);
    recyclerManager.setAdapter(adapter);

    return recyclerManager;
  }

}
