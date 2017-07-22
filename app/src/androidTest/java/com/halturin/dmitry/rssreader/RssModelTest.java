package com.halturin.dmitry.rssreader;

import android.support.test.runner.AndroidJUnit4;

import com.halturin.dmitry.rssreader.model.RssModel;
import com.halturin.dmitry.rssreader.model.RssModelImpl;
import com.halturin.dmitry.rssreader.model.dto.FeedEntity;
import com.halturin.dmitry.rssreader.model.dto.ItemEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import rx.observers.AssertableSubscriber;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class RssModelTest {

    private static final String rssFeedParserBrokeUrl = "http://google.com";
    private static final String[] rssFeedUrls = {
        "http://feeds.bbci.co.uk/news/education/rss.xml",
        "http://feeds.bbci.co.uk/news/technology/rss.xml",
        "http://feeds.bbci.co.uk/news/politics/rss.xml"
    };


    private String getEnclosingMethodName(Object object){
        return object.getClass().getEnclosingMethod().getName();
    }

    private <T> T getFirstItem(AssertableSubscriber<T> subscriber){
        return subscriber.getOnNextEvents().get(0);
    }

    private void setFeedUrlsToModel(final RssModel rssModel, final String[] urls) throws Exception {
        for(String url : urls){
            AssertableSubscriber<Void> subscriber = rssModel.setFeed(url).test();

            subscriber
                .awaitTerminalEvent()
                .assertNoErrors()
                .assertCompleted();
        }
    }

    private boolean getUpdateFeedFromModel(final RssModel rssModel) throws Exception {
        AssertableSubscriber<Boolean> subscriber = rssModel.getUpdateFeed().test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        return getFirstItem(subscriber);
    }

    private List<FeedEntity> getFeedsListFromModel(final RssModel rssModel){
        AssertableSubscriber<List<FeedEntity>> subscriber = rssModel.getFeedsList().test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        return getFirstItem(subscriber);
    }

    private FeedEntity getFeedFromModel(final RssModel rssModel){
        AssertableSubscriber<FeedEntity> subscriber = rssModel.getFeed().test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        return getFirstItem(subscriber);
    }

    private List<ItemEntity> getItemsListFromModel(final RssModel rssModel){
        AssertableSubscriber<List<ItemEntity>> subscriber = rssModel.getItemsList().test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        return getFirstItem(subscriber);
    }

    private ItemEntity getItemFromModel(final RssModel rssModel, final long id){
        AssertableSubscriber<ItemEntity> subscriber = rssModel.getItem(id).test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        return getFirstItem(subscriber);
    }


    @Test
    public void processingEmptyRssModel() throws Exception {
        String modelName = getEnclosingMethodName(new Object(){});

        RssModel rssModel = new RssModelImpl(modelName);

        boolean success = getUpdateFeedFromModel(rssModel);

        assertFalse(success);

        List<FeedEntity> feeds = getFeedsListFromModel(rssModel);
        int feedsSize = feeds.size();

        assertEquals(0, feedsSize);

        FeedEntity feed = getFeedFromModel(rssModel);

        assertNull(feed);

        List<ItemEntity> items = getItemsListFromModel(rssModel);
        int itemsSize = items.size();

        assertEquals(0, itemsSize);
    }

    @Test
    public void processingFilledRssModel() throws Exception {
        String modelName = getEnclosingMethodName(new Object(){});

        RssModel rssModel = new RssModelImpl(modelName);

        setFeedUrlsToModel(rssModel, rssFeedUrls);

        boolean success = getUpdateFeedFromModel(rssModel);

        assertTrue(success);

        List<FeedEntity> feeds = getFeedsListFromModel(rssModel);
        int urlsSize = rssFeedUrls.length;
        int feedsSize = feeds.size();

        assertEquals(urlsSize, feedsSize);

        FeedEntity feed = getFeedFromModel(rssModel);

        assertNotNull(feed);

        List<ItemEntity> items = getItemsListFromModel(rssModel);
        int itemsSize = items.size();

        assertNotEquals(0, itemsSize);

        ItemEntity item = items.get(0);

        assertNotNull(item);

        long itemId = item.getId();

        ItemEntity sameItem = getItemFromModel(rssModel, itemId);

        assertNotNull(sameItem);
        assertEquals(item, sameItem);
    }

    @Test
    public void addSameUrlsToRssModel() throws Exception {
        String modelName = getEnclosingMethodName(new Object(){});

        RssModel rssModel = new RssModelImpl(modelName);

        setFeedUrlsToModel(rssModel, rssFeedUrls);

        String[] urls = new String[]{rssFeedUrls[0]};

        setFeedUrlsToModel(rssModel, urls);

        List<FeedEntity> feeds = getFeedsListFromModel(rssModel);
        int urlsSize = rssFeedUrls.length;
        int feedsSize = feeds.size();

        assertEquals(urlsSize, feedsSize);
    }

    @Test
    public void addBrokeUrlToRssModel() throws Exception {
        String modelName = getEnclosingMethodName(new Object(){});

        RssModel rssModel = new RssModelImpl(modelName);

        String[] urls = new String[]{rssFeedParserBrokeUrl};

        setFeedUrlsToModel(rssModel, urls);

        AssertableSubscriber<Boolean> subscriber = rssModel.getUpdateFeed().test();

        subscriber
            .awaitTerminalEvent()
            .assertError(Exception.class);
    }

    @Test
    public void processingCleanedRssModel() throws Exception {
        String modelName = getEnclosingMethodName(new Object(){});

        RssModel rssModel = new RssModelImpl(modelName);

        setFeedUrlsToModel(rssModel, rssFeedUrls);

        boolean success = getUpdateFeedFromModel(rssModel);

        assertTrue(success);

        FeedEntity feed;

        feed = getFeedFromModel(rssModel);

        assertNotNull(feed);

        long feedId = feed.getId();

        List<ItemEntity> items;
        int itemsSize;

        items = getItemsListFromModel(rssModel);
        itemsSize = items.size();

        assertNotEquals(0, itemsSize);

        ItemEntity item = items.get(0);

        assertNotNull(item);

        long itemId = item.getId();

        AssertableSubscriber<?> subscriber;

        subscriber = rssModel.removeAllFeeds().test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        subscriber = rssModel.removeFeed(feedId).test();

        subscriber
            .assertError(Exception.class);

        List<FeedEntity> feeds = getFeedsListFromModel(rssModel);
        int feedsSize = feeds.size();

        assertEquals(0, feedsSize);

        feed = getFeedFromModel(rssModel);

        assertNull(feed);

        items = getItemsListFromModel(rssModel);
        itemsSize = items.size();

        assertEquals(0, itemsSize);

        subscriber = rssModel.getItem(itemId).test();

        subscriber
            .assertError(Exception.class);
    }

    @Test
    public void feedItemChangedIsReaded() throws Exception {
        String modelName = getEnclosingMethodName(new Object(){});

        RssModel rssModel = new RssModelImpl(modelName);

        setFeedUrlsToModel(rssModel, rssFeedUrls);

        boolean success = getUpdateFeedFromModel(rssModel);

        assertTrue(success);

        List<ItemEntity> items;
        int itemsSize;

        items = getItemsListFromModel(rssModel);
        itemsSize = items.size();

        assertNotEquals(0, itemsSize);

        ItemEntity item;

        item = items.get(0);

        assertNotNull(item);

        long itemId = item.getId();

        ItemEntity sameItem = getItemFromModel(rssModel, itemId);

        assertNotNull(sameItem);
        assertEquals(item, sameItem);

        AssertableSubscriber<ItemEntity> subscriber;

        subscriber = rssModel.getItem(itemId).test();

        subscriber
            .awaitTerminalEvent()
            .assertNoErrors()
            .assertCompleted();

        item = getFirstItem(subscriber);

        boolean itemIsReaded = item.isReaded();

        assertTrue(itemIsReaded);
    }

}
