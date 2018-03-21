package com.khalturin.dmitriy.presentation.mapper;

import com.khalturin.dmitriy.presentation.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 18.08.17 10:53.
 */

public class FeedMapper implements Func1<List<ItemEntity>, List<NewsModel>> {

    @Override
    public List<NewsModel> call(List<ItemEntity> itemEntities){
        List<NewsModel> list = new ArrayList<>();

        for(ItemEntity entity : itemEntities){
            NewsModel news = new NewsModel();

//            news.set(entity);
            list.add(news);
        }

        return list;
    }

}

class ItemEntity {
}
