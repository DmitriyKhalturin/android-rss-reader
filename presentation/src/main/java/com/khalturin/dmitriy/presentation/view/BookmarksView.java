package com.khalturin.dmitriy.presentation.view;

import com.khalturin.dmitriy.presentation.viewmodel.bookmark.RssFeedViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:18.
 */

public interface BookmarksView {

    void setBookmarksItems(List<RssFeedViewModel> list);

    Observable<String> getOnSearchChange();

    Observable<Long> getOnLoadFeed();

    void setLoadFeedComplete();

    Observable<Long> getOnDeleteFeed();

    void setDeleteFeedComplete();

}
