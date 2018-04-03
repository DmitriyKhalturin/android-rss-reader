package com.khalturin.dmitriy.presentation.viewmodel.bookmark;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.khalturin.dmitriy.presentation.binding.BindingTransformer;
import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerManager;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static com.khalturin.dmitriy.presentation.viewmodel.DefaultFieldValue.EMPTY_STRING;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 03.04.18 9:58.
 */

public class BookmarksViewModel extends ViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private PublishSubject<Long> onLoadRss = PublishSubject.create();
  private PublishSubject<Long> onDeleteRss = PublishSubject.create();

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<RecyclerManager> recyclerManager = new ObservableField<>();
  public ObservableField<String> searchText = new ObservableField<>(EMPTY_STRING);

  public void loadRss(Long rssId){
    onLoadRss.onNext(rssId);
  }

  public void deleteRss(Long rssId){
    onDeleteRss.onNext(rssId);
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  public void setBookmarksItems(List<RssViewModel> items){
    getAdapter().setItems(items);
  }

  @SuppressWarnings("unchecked")
  private BindingRecyclerAdapter<RssViewModel> getAdapter(){
    return (BindingRecyclerAdapter<RssViewModel>) recyclerManager.get().getAdapter();
  }

  public Observable<String> getOnSearchChange(){
    int timeout = 1000;

    return BindingTransformer.toObservable(searchText)
      .debounce(timeout, MILLISECONDS);
  }

  public Observable<Long> getOnLoadRss(){
    return onLoadRss;
  }

  public Observable<Long> getOnDeleteRss(){
    return onDeleteRss;
  }

}
