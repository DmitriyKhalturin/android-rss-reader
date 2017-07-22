package com.halturin.dmitry.rssreader.model;

import android.support.annotation.NonNull;

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
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:03.
 */

public class RssModelImpl implements RssModel {

    private RealmConfiguration realmConfiguration = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public RssModelImpl(){
    }

    public RssModelImpl(@NonNull final String modelName){
        realmConfiguration = new RealmConfiguration.Builder()
            .name(modelName)
            .build();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private Realm getRealmInstance(){
        Realm realm;

        if(realmConfiguration != null){
            realm = Realm.getInstance(realmConfiguration);
        }else{
            realm = DatabaseModule.getInstance();
        }

        return realm;
    }

    private FeedEntity getActiveFeedEntity(){
        Realm realm = getRealmInstance();

        FeedEntity feedEntity = realm.where(FeedEntity.class)
            .equalTo("isActive", true)
            .findFirst();

        return feedEntity;
    }

    private void clearActiveFlagFromAllFeedEntities(){
        Realm realm = getRealmInstance();

        try{
            RealmResults<FeedEntity> feedEntities = realm.where(FeedEntity.class)
                .equalTo("isActive", true)
                .findAll();

            for(FeedEntity feedEntity : feedEntities){
                realm.beginTransaction();

                feedEntity.setActive(false);

                realm.commitTransaction();
            }
        }finally{
            realm.close();
        }
    }

    private void setActiveFlagForFeedEntity(final String url){
        Realm realm = getRealmInstance();

        try{
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
        }finally{
            realm.close();
        }
    }

    private InputStream getFeedStream(final String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        InputStream stream = response.body().byteStream();

        return stream;
    }

    private void copyFeedToRealm(final Feed feed, final FeedEntity feedEntity){
        Realm realm = getRealmInstance();

        try{
            realm.beginTransaction();

            feedEntity.setAuthor(feed.getAuthor());
            feedEntity.setTitle(feed.getTitle());
            feedEntity.setDescription(feed.getDescription());
            feedEntity.setLink(feed.getLink());
            feedEntity.setImage(feed.getImageLink());
            feedEntity.setDate(feed.getPublicationDate());
            feedEntity.setCopyright(feed.getCopyright());

            realm.copyToRealmOrUpdate(feedEntity);
            realm.commitTransaction();
        }finally{
            realm.close();
        }
    }

    private void copyItemsToRealm(final List<? extends Item> items, final FeedEntity feedEntity){
        Realm realm = getRealmInstance();

        try{
            long feedId = feedEntity.getId();

            for(Item item : items){
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
        }finally{
            realm.close();
        }
    }

    private List<FeedEntity> getFeedEntities(){
        Realm realm = getRealmInstance();

        RealmResults<FeedEntity> feedEntities = realm.where(FeedEntity.class)
            .findAll();

        return feedEntities;
    }

    private void removeFeedEntity(final long id){
        Realm realm = getRealmInstance();

        try{
            FeedEntity feedEntity = realm.where(FeedEntity.class)
                .equalTo("id", id)
                .findFirst();

            long feedId = feedEntity.getId();

            removeAllItemEntities(feedId);

            feedEntity.deleteFromRealm();
        }finally{
            realm.close();
        }
    }

    private void removeAllFeedEntities(){
        Realm realm = getRealmInstance();

        try{
            RealmResults<FeedEntity> feedEntities = realm.where(FeedEntity.class)
                .findAll();

            for(FeedEntity feedEntity : feedEntities){
                long feedId = feedEntity.getId();

                removeAllItemEntities(feedId);
            }

            realm.beginTransaction();

            feedEntities.deleteAllFromRealm();
            realm.commitTransaction();
        }finally{
            realm.close();
        }
    }

    private void removeAllItemEntities(final long feedId){
        Realm realm = getRealmInstance();

        try{
            RealmResults<ItemEntity> itemEntities = realm.where(ItemEntity.class)
                .equalTo("feedId", feedId)
                .findAll();

            realm.beginTransaction();

            itemEntities.deleteAllFromRealm();
            realm.commitTransaction();
        }finally{
            realm.close();
        }
    }

    private List<ItemEntity> getItemsEntities(final FeedEntity feedEntity){
        Realm realm = getRealmInstance();

        List<ItemEntity> itemEntities;

        if(feedEntity != null){
            long feedId = feedEntity.getId();

            itemEntities = realm.where(ItemEntity.class)
                .equalTo("feedId", feedId)
                .findAll();
        }else{
            itemEntities = new ArrayList<>();
        }

        return itemEntities;
    }

    private ItemEntity getItemEntity(final long id){
        Realm realm = getRealmInstance();

        ItemEntity itemEntity = realm.where(ItemEntity.class)
            .equalTo("id", id)
            .findFirst();

        realm.beginTransaction();

        itemEntity.setReaded(true);

        realm.commitTransaction();

        return itemEntity;
    }

//==================================================================================================
//    Class Implementation RssModel
//==================================================================================================

    @Override
    public Observable<FeedEntity> getFeed(){
        return Observable.create(subscriber -> {
            try{
                FeedEntity feedEntity = getActiveFeedEntity();

                subscriber.onNext(feedEntity);
                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<Void> setFeed(final String url){
        return Observable.create(subscriber -> {
            try{
                clearActiveFlagFromAllFeedEntities();
                setActiveFlagForFeedEntity(url);

                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<Boolean> getUpdateFeed(){
        return Observable.create(subscriber -> {
            try{
                FeedEntity feedEntity = getActiveFeedEntity();

                if(feedEntity != null){
                    String url = feedEntity.getUrl();
                    InputStream stream = getFeedStream(url);
                    Feed feed = EarlParser.parseOrThrow(stream, 0);
                    copyFeedToRealm(feed, feedEntity);
                    copyItemsToRealm(feed.getItems(), feedEntity);

                    subscriber.onNext(true);
                }else{
                    subscriber.onNext(false);
                }

                feedEntity = getActiveFeedEntity();

                subscriber.onCompleted();
            }catch(IOException | XmlPullParserException | DataFormatException error){
                subscriber.onError(error);
            }
        }).compose(ObservableTransformer.applyMainThreadScheduler());
    }

    @Override
    public Observable<List<FeedEntity>> getFeedsList(){
        return Observable.create(subscriber -> {
            try{
                List<FeedEntity> feedEntities = getFeedEntities();

                subscriber.onNext(feedEntities);
                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<Void> removeFeed(final long id){
        return Observable.create(subscriber -> {
            try{
                removeFeedEntity(id);

                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<Void> removeAllFeeds(){
        return Observable.create(subscriber -> {
            try{
                removeAllFeedEntities();

                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<List<ItemEntity>> getItemsList(){
        return Observable.create(subscriber -> {
            try{
                FeedEntity feedEntity = getActiveFeedEntity();

                List<ItemEntity> itemEntities = getItemsEntities(feedEntity);

                subscriber.onNext(itemEntities);
                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

    @Override
    public Observable<ItemEntity> getItem(final long id){
        return Observable.create(subscriber -> {
            try{
                ItemEntity itemEntity = getItemEntity(id);

                subscriber.onNext(itemEntity);
                subscriber.onCompleted();
            }catch(Throwable error){
                subscriber.onError(error);
            }
        });
    }

}
