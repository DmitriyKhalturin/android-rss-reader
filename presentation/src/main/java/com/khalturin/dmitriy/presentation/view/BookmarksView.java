package com.khalturin.dmitriy.presentation.view;

import com.khalturin.dmitriy.presentation.viewmodel.bookmark.RssViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:18.
 */

public interface BookmarksView {

    void setBookmarksItems(List<RssViewModel> list);

    Observable<String> getOnSearchChange();

    Observable<Long> getOnLoadRss();

    Observable<Long> getOnDeleteRss();

}
