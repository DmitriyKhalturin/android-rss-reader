package com.halturin.dmitry.rssreader.view;

import com.halturin.dmitry.rssreader.presenter.vo.News;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:19.
 */

public interface NewsView {

    long getNewsId();

    void setContent(News news);

}
