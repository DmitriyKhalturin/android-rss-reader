package com.halturin.dmitry.rssreader.presenter.mapper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.halturin.dmitry.rssreader.model.dto.ItemEntity;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 22.02.17 3:27.
 */

public class NewsListMapper implements Func1<List<ItemEntity>, List<News>> {

    @Override
    public List<News> call(List<ItemEntity> itemEntities){
        List<News> list = null;

        if(itemEntities != null){
            list = Observable.from(itemEntities)
                .map(itemEntity -> {
                    News news = new News();

                    news.setId(itemEntity.getId());
                    news.setTitle(itemEntity.getTitle());
                    news.setDescription(itemEntity.getDescription());
                    news.setLink(itemEntity.getLink());
                    news.setDate(itemEntity.getDate().toString());
                    news.setReaded(itemEntity.isReaded());

                    try{
                        URL url = new URL(itemEntity.getImage());
                        Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        news.setImage(bitmap);
                    }catch(IOException error){
                        // TODO: processing error
                    }

                    return news;
                })
                .toList()
                .toBlocking()
                .first();
        }

        return list;
    }

}
