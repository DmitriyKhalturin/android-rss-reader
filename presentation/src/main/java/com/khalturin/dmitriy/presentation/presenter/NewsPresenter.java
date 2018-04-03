package com.khalturin.dmitriy.presentation.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.khalturin.dmitriy.presentation.viewmodel.news.NewsViewModel;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:01.
 */

public class NewsPresenter extends ViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private MutableLiveData<NewsViewModel> mNewsViewModel = new MutableLiveData<>();

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  public LiveData<NewsViewModel> getNewsViewModel(){
      return mNewsViewModel;
  }

  public void setNewsId(@NonNull Long newsId){
    if(newsId.equals(getNewsId())){
      // TODO: get news id add load to view model
    }
  }

  private Long getNewsId(){
    Long newsId = null;
    NewsViewModel newsViewModel = mNewsViewModel.getValue();

    if(newsViewModel != null){
      newsId = newsViewModel.id;
    }

    return newsId;
  }

}
