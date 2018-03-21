package com.khalturin.dmitriy.presentation.view;

import com.khalturin.dmitriy.presentation.model.NewsModel;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:19.
 */

public interface NewsView {

    long getNewsId();

    void setContent(NewsModel news);

}
