package com.khalturin.dmitriy.rssreader.view.viewmodel.bookmark;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.rssreader.view.binding.BindingTransformer;

import java.util.List;

import dmitriy.khalturin.com.view.recycler.binding.RecyclerManager;
import dmitriy.khalturin.com.view.recycler.binding.adapter.BindingRecyclerAdapter;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.EMPTY_STRING;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 03.04.18 9:58.
 */

public class BookmarksViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private PublishSubject<Long> onLoadFeed = PublishSubject.create();
  private PublishSubject<Long> onDeleteFeed = PublishSubject.create();

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<RecyclerManager> recyclerManager = new ObservableField<>();
  public ObservableField<String> searchFeedText = new ObservableField<>(EMPTY_STRING);

  public void loadFeed(Long rssId){
    onLoadFeed.onNext(rssId);
  }

  public void deleteFeed(Long rssId){
    onDeleteFeed.onNext(rssId);
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  public void setBookmarksItems(List<FeedViewModel> items){
    getAdapter().setItems(items);
  }

  @SuppressWarnings("unchecked")
  private BindingRecyclerAdapter<FeedViewModel> getAdapter(){
    return (BindingRecyclerAdapter<FeedViewModel>) recyclerManager.get().getAdapter();
  }

  public Observable<String> getOnSearchFeed(){
    int timeout = 1000;

    return BindingTransformer.toObservable(searchFeedText)
      .debounce(timeout, MILLISECONDS);
  }

  public Observable<Long> getOnLoadFeed(){
    return onLoadFeed;
  }

  public Observable<Long> getOnDeleteFeed(){
    return onDeleteFeed;
  }

}
