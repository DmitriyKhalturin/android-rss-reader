package com.halturin.dmitry.rssreader.model;

import com.halturin.dmitry.rssreader.model.dto.FeedEntity;
import com.halturin.dmitry.rssreader.model.dto.ItemEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:03.
 */

public interface RssModel {

    Observable<FeedEntity> getFeed();

    Observable<Void> setFeed(String url);

    Observable<Void> setFeed(long id);

    Observable<Boolean> getUpdateFeed();

    Observable<List<FeedEntity>> getFeedsList();

    Observable<List<FeedEntity>> getFeedsListWithSearch(String searchText);

    Observable<Void> removeFeed(long id);

    Observable<Void> removeAllFeeds();

    Observable<List<ItemEntity>> getItemsList();

    Observable<ItemEntity> getItem(long id);

}
