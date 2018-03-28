package com.khalturin.dmitriy.presentation.viewmodel;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerConfigurator;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;

import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:19.
 */

public class FeedViewModel {

  public ObservableField<RecyclerConfigurator> recyclerConfigurator = new ObservableField<>();

  public void setItems(List<NewsViewModel> items){
    getAdapter().setItems(items);
  }

  public int getItemCount(){
    return getAdapter().getItemCount();
  }

  @SuppressWarnings("unchecked")
  private BindingRecyclerAdapter<NewsViewModel> getAdapter(){
    return (BindingRecyclerAdapter<NewsViewModel>) recyclerConfigurator.get().getAdapter();
  }

}
