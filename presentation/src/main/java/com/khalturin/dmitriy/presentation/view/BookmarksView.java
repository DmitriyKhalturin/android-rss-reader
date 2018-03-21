package com.khalturin.dmitriy.presentation.view;

import com.khalturin.dmitriy.presentation.model.FeedModel;

import java.util.List;

import rx.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:18.
 */

public interface BookmarksView {

    void setList(List<FeedModel> list);

    Observable<CharSequence> getOnSearchChange();

    Observable<Long> getOnLoadFeed();

    void setLoadFeedComplete();

    Observable<Long> getOnDeleteFeed();

    void setDeleteFeedComplete();

}
