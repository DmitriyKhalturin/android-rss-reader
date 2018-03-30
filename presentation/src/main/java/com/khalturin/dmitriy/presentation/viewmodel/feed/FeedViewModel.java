package com.khalturin.dmitriy.presentation.viewmodel.feed;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerManager;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;
import com.khalturin.dmitriy.presentation.viewmodel.news.NewsViewModel;

import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:19.
 */

public class FeedViewModel {

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<RecyclerManager> recyclerManager = new ObservableField<>();

//==================================================================================================
//    Class Methods
//==================================================================================================

  public void setFeedItems(List<NewsViewModel> items){
    getAdapter().setItems(items);
  }

  public int getFeedItemCount(){
    return getAdapter().getItemCount();
  }

  @SuppressWarnings("unchecked")
  private BindingRecyclerAdapter<NewsViewModel> getAdapter(){
    return (BindingRecyclerAdapter<NewsViewModel>) recyclerManager.get().getAdapter();
  }

}
