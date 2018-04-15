package com.khalturin.dmitriy.presentation.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.khalturin.dmitriy.presentation.viewmodel.bookmark.BookmarksViewModel;

import dmitriy.khalturin.com.view.recycler.binding.RecyclerManager;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:01.
 */

public class BookmarksPresenter extends ViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private MutableLiveData<BookmarksViewModel> mBookmarksViewModel = new MutableLiveData<>();

//==================================================================================================
//    Class Constructor
//==================================================================================================

  public BookmarksPresenter(){
    setBookmarksViewModel(new BookmarksViewModel());
  }

//==================================================================================================
//    Class Callback
//==================================================================================================

  @Override
  protected void onCleared(){
    // TODO: Clear all resources and break actions in this scope.

    super.onCleared();
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  private BookmarksViewModel getBookmarksViewModel(){
    return mBookmarksViewModel.getValue();
  }

  private void setBookmarksViewModel(BookmarksViewModel bookmarksViewModel){
    mBookmarksViewModel.setValue(bookmarksViewModel);
  }

  public LiveData<BookmarksViewModel> getBookmarksObserve(){
    return mBookmarksViewModel;
  }

  public void setRecyclerManager(RecyclerManager recyclerManager){
    getBookmarksViewModel().recyclerManager.set(recyclerManager);
  }

  public void setActionsListeners(){
    BookmarksViewModel bookmarksViewModel = getBookmarksViewModel();

    bookmarksViewModel.getOnSearchFeed()
      .subscribe(this::onSearchFeed);
    bookmarksViewModel.getOnLoadFeed()
      .subscribe(this::onLoadFeed);
    bookmarksViewModel.getOnDeleteFeed()
      .subscribe(this::onDeleteFeed);
  }

  private void onSearchFeed(String searchText){
    // TODO: implementation later
  }

  private void onLoadFeed(Long feedId){
    // TODO: implementation later
  }

  private void onDeleteFeed(Long feedId){
    // TODO: implementation later
  }

}
