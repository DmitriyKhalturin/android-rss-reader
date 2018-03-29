package com.khalturin.dmitriy.presentation.view;

import com.khalturin.dmitriy.presentation.viewmodel.news.NewsViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:18.
 */

public interface FeedView {

  Observable<String> getOnUpdateRssUrl();

  void setUpdateRssUrlComplete();

  void setFeedItems(List<NewsViewModel> items);

  Observable<Boolean> getOnUpdateFeedItems();

  void setUpdateFeedItemsComplete();

}
