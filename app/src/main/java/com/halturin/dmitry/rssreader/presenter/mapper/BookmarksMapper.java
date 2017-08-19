package com.halturin.dmitry.rssreader.presenter.mapper;

import com.halturin.dmitry.rssreader.model.dto.FeedEntity;
import com.halturin.dmitry.rssreader.presenter.vo.Feed;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.08.17 5:55.
 */

public class BookmarksMapper implements Func1<List<FeedEntity>, List<Feed>> {

    @Override
    public List<Feed> call(List<FeedEntity> feedEntities){
        List<Feed> list = new ArrayList<>();

        for(FeedEntity entity : feedEntities){
            Feed feed = new Feed();

            feed.set(entity);
            list.add(feed);
        }

        return list;
    }

}
