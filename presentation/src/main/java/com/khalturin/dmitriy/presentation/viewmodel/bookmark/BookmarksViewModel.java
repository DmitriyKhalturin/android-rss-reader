package com.khalturin.dmitriy.presentation.viewmodel.bookmark;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.presentation.binding.BindingTransformer;
import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerManager;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;

import java.util.List;

import io.reactivex.Observable;

import static com.khalturin.dmitriy.presentation.viewmodel.DefaultFieldValue.EMPTY_STRING;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 03.04.18 9:58.
 */

public class BookmarksViewModel {

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<RecyclerManager> recyclerManager = new ObservableField<>();
  public ObservableField<String> searchText = new ObservableField<>(EMPTY_STRING);

//==================================================================================================
//    Class Methods
//==================================================================================================

  public void setBookmarksItems(List<RssFeedViewModel> items){
    getAdapter().setItems(items);
  }

  @SuppressWarnings("unchecked")
  private BindingRecyclerAdapter<RssFeedViewModel> getAdapter(){
    return (BindingRecyclerAdapter<RssFeedViewModel>) recyclerManager.get().getAdapter();
  }

  public Observable<String> getOnSearchChange(){
    int timeout = 1000;

    return BindingTransformer.toObservable(searchText)
      .debounce(timeout, MILLISECONDS);
  }

}
