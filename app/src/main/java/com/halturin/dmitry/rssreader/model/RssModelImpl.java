package com.halturin.dmitry.rssreader.model;

import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;
import com.halturin.dmitry.rssreader.app.transformer.ObservableTransformer;
import com.halturin.dmitry.rssreader.model.database.DatabaseModule;
import com.halturin.dmitry.rssreader.model.dto.FeedEntity;
import com.halturin.dmitry.rssreader.model.dto.ItemEntity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:03.
 */

public class RssModelImpl implements RssModel {

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public RssModelImpl(){
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private Realm getInstance(){
        return DatabaseModule.getInstance();
    }

    private long copyFeedToRealm(Feed feed, String url, Realm realm){
        FeedEntity feedEntity = realm.where(FeedEntity.class)
            .equalTo("url", url)
            .findFirst();

        realm.beginTransaction();

        // it is new feed entity in realm
        if(feedEntity == null){
            feedEntity = new FeedEntity();

            feedEntity.autoIncrementId(realm);

            feedEntity.setUrl(url);
        }

        long feedId = feedEntity.getId();

        feedEntity.setAuthor(feed.getAuthor());
        feedEntity.setTitle(feed.getTitle());
        feedEntity.setDescription(feed.getDescription());
        feedEntity.setLink(feed.getLink());
        feedEntity.setImage(feed.getImageLink());
        feedEntity.setDate(feed.getPublicationDate());
        feedEntity.setCopyright(feed.getCopyright());

        realm.copyToRealmOrUpdate(feedEntity);
        realm.commitTransaction();

        return feedId;
    }

    private void copyItemToRealm(Item item, long feedId, Realm realm){
        String itemId = item.getId();
        ItemEntity itemEntity = realm.where(ItemEntity.class)
            .equalTo("feedId", feedId)
            .equalTo("itemId", itemId)
            .findFirst();

        realm.beginTransaction();

        // it is new item entity in realm
        if(itemEntity == null){
            itemEntity = new ItemEntity();

            itemEntity.autoIncrementId(realm);

            itemEntity.setFeedId(feedId);
            itemEntity.setItemId(itemId);
        }

        itemEntity.setAuthor(item.getAuthor());
        itemEntity.setTitle(item.getTitle());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setLink(item.getLink());
        itemEntity.setImage(item.getImageLink());
        itemEntity.setDate(item.getPublicationDate());

        realm.copyToRealmOrUpdate(itemEntity);
        realm.commitTransaction();
    }

    private void clearRealmFromFeed(String url, Realm realm){
        RealmResults<FeedEntity> feedEntities = realm.where(FeedEntity.class)
            .equalTo("url", url)
            .findAll();

        for(FeedEntity feedEntity : feedEntities){
            long feedId = feedEntity.getId();

            RealmResults<ItemEntity> itemEntities = realm.where(ItemEntity.class)
                .equalTo("feedId", feedId)
                .findAll();

            itemEntities.deleteAllFromRealm();
        }

        feedEntities.deleteAllFromRealm();
    }

    private void unsetAllActiveFlagInFeed(Realm realm){
        RealmResults<FeedEntity> feedEntities = realm.where(FeedEntity.class)
            .equalTo("isActive", true)
            .findAll();

        for(FeedEntity feedEntity : feedEntities){
            realm.beginTransaction();

            feedEntity.setActive(false);

            realm.commitTransaction();
        }
    }

    private void setActiveFlagInFeed(String url, Realm realm){
        FeedEntity feedEntity = realm.where(FeedEntity.class)
            .equalTo("url", url)
            .findFirst();

        realm.beginTransaction();

        // it is new feed entity in realm
        if(feedEntity == null){
            feedEntity = new FeedEntity();

            feedEntity.autoIncrementId(realm);

            feedEntity.setUrl(url);
        }

        feedEntity.setActive(true);

        realm.copyToRealmOrUpdate(feedEntity);
        realm.commitTransaction();
    }

//==================================================================================================
//    Class Implementation RssModel
//==================================================================================================

    @Override
    public String getUrl(){
        String url = null;
        Realm realm = getInstance();

        FeedEntity feedEntity = realm.where(FeedEntity.class)
            .equalTo("isActive", true)
            .findFirst();

        if(feedEntity != null){
            url = feedEntity.getUrl();
        }

        return url;
    }

    @Override
    public void setUrl(String url){
        Realm realm = getInstance();

        unsetAllActiveFlagInFeed(realm);
        setActiveFlagInFeed(url, realm);
    }

    @Override
    public Observable<Void> updateFeed(){
        return Observable.create(subscriber -> {
            Realm realm = getInstance();

            try{
                String url = getUrl();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                InputStream stream = response.body().byteStream();

                Feed feed = EarlParser.parseOrThrow(stream, 0);

                long feedId = copyFeedToRealm(feed, url, realm);

                for(Item item : feed.getItems()){
                    copyItemToRealm(item, feedId, realm);
                }

                subscriber.onNext(null);
                subscriber.onCompleted();
            }catch(NullPointerException | IOException | XmlPullParserException | DataFormatException error){
                subscriber.onError(error);
            }
        }).compose(ObservableTransformer.applySchedulers());
    }

    @Override
    public Observable<RealmResults<ItemEntity>> getFeed(){
        return Observable.create(subscriber -> {
            Realm realm = getInstance();

            try{
                String url = getUrl();
                FeedEntity feedEntity = realm.where(FeedEntity.class)
                    .equalTo("url", url)
                    .findFirst();
                long feedId = feedEntity.getId();

                RealmResults<ItemEntity> itemEntities = realm.where(ItemEntity.class)
                    .equalTo("feedId", feedId)
                    .findAll();

                subscriber.onNext(itemEntities);
                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<ItemEntity> getNews(long id){
        return Observable.create(subscriber -> {
            Realm realm = getInstance();

            try{
                ItemEntity itemEntity = realm.where(ItemEntity.class)
                    .equalTo("id", id)
                    .findFirst();

                realm.beginTransaction();

                itemEntity.setReaded(true);

                realm.commitTransaction();

                subscriber.onNext(itemEntity);
                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

}
