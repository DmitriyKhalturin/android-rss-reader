package com.halturin.dmitry.rssreader.view;

import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.util.List;

import rx.Observable;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:18.
 */

public interface FeedView {

    void setList(List<News> newsList);

    Observable<Void> getOnUpdateFeed();

    void setUpdateComplete();

}
