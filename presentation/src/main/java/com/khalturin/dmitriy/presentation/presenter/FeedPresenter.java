package com.khalturin.dmitriy.presentation.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import com.khalturin.dmitriy.presentation.RssApplication;
import com.khalturin.dmitriy.presentation.navigator.Navigator;
import com.khalturin.dmitriy.presentation.viewmodel.feed.FeedViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.feed.RssUrlViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.feed.SwipeRefreshViewModel;

import javax.inject.Inject;

import dmitriy.khalturin.com.view.recycler.binding.RecyclerManager;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:01.
 */

public class FeedPresenter extends ViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  @Inject
  protected Navigator mNavigator;

  private MutableLiveData<FeedViewModel> mFeedViewModel = new MutableLiveData<>();
  private MutableLiveData<RssUrlViewModel> mRssUrlViewModel = new MutableLiveData<>();
  private MutableLiveData<SwipeRefreshViewModel> mSwipeRefreshViewModel = new MutableLiveData<>();

//==================================================================================================
//    Class Constructor
//==================================================================================================

  public FeedPresenter(){
    setFeedViewModel(new FeedViewModel());
    setRssUrlViewModel(new RssUrlViewModel());
    setSwipeRefreshViewModel(new SwipeRefreshViewModel());

    RssApplication.getInjector().getAppComponent().inject(this);
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

  private FeedViewModel getFeedViewModel(){
    return mFeedViewModel.getValue();
  }

  private void setFeedViewModel(FeedViewModel feedViewModel){
    mFeedViewModel.setValue(feedViewModel);
  }

  private RssUrlViewModel getRssUrlViewModel(){
    return mRssUrlViewModel.getValue();
  }

  private void setRssUrlViewModel(RssUrlViewModel rssUrlViewModel){
    mRssUrlViewModel.setValue(rssUrlViewModel);
  }

  private SwipeRefreshViewModel getSwipeRefreshViewModel(){
    return mSwipeRefreshViewModel.getValue();
  }

  private void setSwipeRefreshViewModel(SwipeRefreshViewModel swipeRefreshViewModel){
    mSwipeRefreshViewModel.setValue(swipeRefreshViewModel);
  }

  public LiveData<FeedViewModel> getFeedObserve(){
    return mFeedViewModel;
  }

  public LiveData<RssUrlViewModel> getRssUrlObserve(){
    return mRssUrlViewModel;
  }

  public LiveData<SwipeRefreshViewModel> getSwipeRefreshObserve(){
    return mSwipeRefreshViewModel;
  }

  public void setRecyclerManager(RecyclerManager recyclerManager){
    getFeedViewModel().recyclerManager.set(recyclerManager);
  }

  public void setActionsListeners(){
    getFeedViewModel().getOnOpenNews()
      .subscribe(mNavigator::navigateToNews);
    getRssUrlViewModel().getOnUpdateRssUrl()
      .subscribe(this::onUpdateRssUrl);
    getSwipeRefreshViewModel().getOnUpdateFeedItems()
      .subscribe(this::onUpdateFeedItems);
  }

  public void setRssUrlLayoutVisibility(){
    int delay = 1000;
    Handler handler = new Handler();

    handler.postDelayed(() -> {
      int size = getFeedViewModel().getFeedItemCount();

      if(size == 0){
        getRssUrlViewModel().isVisible.set(true);
      }
    }, delay);
  }

  public void changeRssUrlLayoutVisibility(){
    getRssUrlViewModel().changeLayoutVisibility();
  }

  public void openBookmarks(){
    mNavigator.navigateToBookmarks();
  }

  private void onUpdateRssUrl(String rssUrl){
    // TODO: implementation later
  }

  private void onUpdateFeedItems(Boolean success){
    // TODO: implementation later
  }

}
