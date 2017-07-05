package com.halturin.dmitry.rssreader.presenter.mapper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.halturin.dmitry.rssreader.model.dto.ItemEntity;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
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
            news.setDate(itemEntity.getDate().toString());
            news.setReaded(itemEntity.isReaded());

            try{
                URL url = new URL(itemEntity.getImage());
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                news.setImage(bitmap);
            }catch(IOException error){
                // TODO: processing error
            }
        }

        return news;
    }

}
