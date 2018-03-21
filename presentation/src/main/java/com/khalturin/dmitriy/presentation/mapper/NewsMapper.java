package com.khalturin.dmitriy.presentation.mapper;

import com.khalturin.dmitriy.presentation.model.NewsModel;

import rx.functions.Func1;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 18.08.17 11:06.
 */

public class NewsMapper implements Func1<ItemEntity, NewsModel> {

    @Override
    public NewsModel call(ItemEntity entity){
        NewsModel news = new NewsModel();

//        news.set(entity);

        return news;
    }

}

class FeedEntity {
}
