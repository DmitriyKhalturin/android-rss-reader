package com.khalturin.dmitriy.presentation.mapper;

import com.khalturin.dmitriy.presentation.model.FeedModel;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.08.17 5:55.
 */

public class BookmarksMapper implements Func1<List<FeedEntity>, List<FeedModel>> {

    @Override
    public List<FeedModel> call(List<FeedEntity> feedEntities){
        List<FeedModel> list = new ArrayList<>();

        for(FeedEntity entity : feedEntities){
            FeedModel feed = new FeedModel();

//            feed.set(entity);
            list.add(feed);
        }

        return list;
    }

}
