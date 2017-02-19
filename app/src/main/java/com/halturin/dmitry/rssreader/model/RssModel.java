package com.halturin.dmitry.rssreader.model;

import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.util.List;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:03.
 */

public interface RssModel {

    String getUrl();

    void setUrl(String url);

    void updateFeed();

    List<News> getFeed();

    News getNews(long id);

}
