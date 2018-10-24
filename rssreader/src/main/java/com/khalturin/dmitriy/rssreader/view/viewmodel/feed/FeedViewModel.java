package com.khalturin.dmitriy.rssreader.view.viewmodel.feed;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.rssreader.view.viewmodel.news.NewsViewModel;

import java.util.List;

import dmitriy.khalturin.com.view.recycler.binding.RecyclerManager;
import dmitriy.khalturin.com.view.recycler.binding.adapter.BindingRecyclerAdapter;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:19.
 */

public class FeedViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private PublishSubject<Long> onOpenNews = PublishSubject.create();

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<RecyclerManager> recyclerManager = new ObservableField<>();

  public void openNews(Long newsId){
    onOpenNews.onNext(newsId);
  }

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

  public Observable<Long> getOnOpenNews(){
    return onOpenNews;
  }

}
