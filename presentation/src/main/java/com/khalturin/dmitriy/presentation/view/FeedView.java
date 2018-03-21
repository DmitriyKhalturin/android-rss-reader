package com.khalturin.dmitriy.presentation.view;

import com.khalturin.dmitriy.presentation.model.NewsModel;

import java.util.List;

import rx.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:18.
 */

public interface FeedView {

    void setList(List<NewsModel> list);

    Observable<String> getOnUpdateUrl();

    void setUpdateUrlComplete();

    Observable<Void> getOnUpdateList();

    void setUpdateListComplete();

}
