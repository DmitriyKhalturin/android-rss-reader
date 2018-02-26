package com.halturin.dmitry.rssreader.presenter.mapper;

import com.halturin.dmitry.rssreader.model.dto.ItemEntity;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import rx.functions.Func1;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 18.08.17 11:06.
 */

public class NewsMapper implements Func1<ItemEntity, News> {

    @Override
    public News call(ItemEntity entity){
        News news = new News();

        news.set(entity);

        return news;
    }

}
