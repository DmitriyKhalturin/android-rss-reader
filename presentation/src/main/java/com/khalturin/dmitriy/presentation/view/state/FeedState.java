package com.khalturin.dmitriy.presentation.view.state;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.presentation.view.adapter.BindingRecyclerAdapter;

import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:19.
 */

public class FeedState {

  public ObservableField<RecyclerConfigurator> recyclerConfigurator = new ObservableField<>();

  public void setNewsList(List<NewsState> collection){
    ((BindingRecyclerAdapter<NewsState>) recyclerConfigurator.get().adapter).setList(collection);
  }

}
