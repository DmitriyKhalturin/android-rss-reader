package com.halturin.dmitry.rssreader.presenter.mapper;

import com.halturin.dmitry.rssreader.model.dto.ItemEntity;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 18.08.17 10:53.
 */

public class FeedMapper implements Func1<List<ItemEntity>, List<News>> {

    @Override
    public List<News> call(List<ItemEntity> itemEntities){
        List<News> list = new ArrayList<>();

        for(ItemEntity entity : itemEntities){
            News news = new News();

            news.set(entity);
            list.add(news);
        }

        return list;
    }

}
