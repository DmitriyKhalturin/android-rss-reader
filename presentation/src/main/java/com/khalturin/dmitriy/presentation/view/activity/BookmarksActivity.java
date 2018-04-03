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
import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerManager;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;
import com.khalturin.dmitriy.presentation.databinding.ActivityBookmarksBinding;
import com.khalturin.dmitriy.presentation.presenter.BookmarksPresenter;
import com.khalturin.dmitriy.presentation.view.BookmarksView;
import com.khalturin.dmitriy.presentation.viewmodel.bookmark.BookmarksViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.bookmark.RssViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 22:00.
 */

public class BookmarksActivity extends AppCompatActivity implements BookmarksView {

//==================================================================================================
//    Class Variables
//==================================================================================================

  BookmarksViewModel mBookmarksViewModel = new BookmarksViewModel();

//==================================================================================================
//    Class Constructor
//==================================================================================================

  public BookmarksActivity(){
//    rssPresenter = new BookmarksPresenter(this);
  }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityBookmarksBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmarks);

    // TODO: check this setter
    mBookmarksViewModel.recyclerManager.set(getRecyclerManager());

    binding.setBookmarksViewModel(mBookmarksViewModel);

    setSupportActionBar(findViewById(R.id.toolbar));

    ActionBar actionBar = getSupportActionBar();

    if(actionBar != null){
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  @SuppressWarnings("unchecked")
  private RecyclerManager getRecyclerManager(){
    RecyclerManager recyclerManager = new RecyclerManager();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    BindingRecyclerAdapter<RssViewModel> adapter = new BindingRecyclerAdapter(R.layout.bookmark_card_view, null, BR.rssViewModel);

    recyclerManager.setLayoutManager(layoutManager);
    recyclerManager.setItemAnimator(itemAnimator);
    recyclerManager.setAdapter(adapter);

    return recyclerManager;
  }

//==================================================================================================
//    Class Implementation BookmarksView
//==================================================================================================

  @Override
  public void setBookmarksItems(List<RssViewModel> items){
    mBookmarksViewModel.setBookmarksItems(items);
  }

  public Observable<String> getOnSearchChange(){
    return mBookmarksViewModel.getOnSearchChange();
  }

  @Override
  public Observable<Long> getOnLoadRss(){
    return mBookmarksViewModel.getOnLoadRss();
  }


  @Override
  public Observable<Long> getOnDeleteRss(){
    return mBookmarksViewModel.getOnDeleteRss();
  }

}
