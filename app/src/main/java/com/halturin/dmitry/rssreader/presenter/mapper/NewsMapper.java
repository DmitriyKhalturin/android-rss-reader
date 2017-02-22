package com.halturin.dmitry.rssreader.presenter.mapper;

import com.halturin.dmitry.rssreader.model.dto.ItemEntity;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import rx.functions.Func1;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 22.02.17 3:28.
 */

public class NewsMapper implements Func1<ItemEntity, News> {

    @Override
    public News call(ItemEntity itemEntity){
        News news = null;

        if(itemEntity != null){
            news = new News();

            news.setTitle(itemEntity.getTitle());
            news.setDescription(itemEntity.getDescription());
            news.setLink(itemEntity.getLink());
            // TODO: set image
            news.setDate(itemEntity.getDate().toString());
            news.setReaded(itemEntity.isReaded());
        }

        return news;
    }

}
