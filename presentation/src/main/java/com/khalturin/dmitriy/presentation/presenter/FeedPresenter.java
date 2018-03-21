package com.khalturin.dmitriy.presentation.presenter;

import com.khalturin.dmitriy.presentation.mapper.FeedMapper;
import com.khalturin.dmitriy.presentation.view.FeedView;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:01.
 */

public class FeedPresenter extends RssPresenterImpl {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private FeedView view = null;
    private FeedMapper mapper = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedPresenter(FeedView view){
        this.view = view;
        this.mapper = new FeedMapper();
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void onResume(){
        super.onResume();

        updateFeedList(true);
        setActionListeners();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void onErrorUpdateFeedList(Throwable error){
        // TODO: show error message
    }

    private void updateFeedList(boolean success){
        if(success){
//            addSubscription(rssModel.getItemsList()
//                .map(mapper)
//                .subscribe(view::setList, this::onErrorUpdateFeedList));
        }
    }

    private void setActionListeners(){
        addSubscription(view.getOnUpdateList()
            .subscribe(this::onUpdateFeed));
        addSubscription(view.getOnUpdateUrl()
            .subscribe(this::onChangeFeedUrl));
    }

    private void onErrorUpdateFeed(Throwable error){
        view.setUpdateListComplete();
        // TODO: show error message
    }

    private void onUpdateFeed(Void aVoid){
//        addSubscription(rssModel.getUpdateFeed()
//            .subscribe(this::updateFeedList, this::onErrorUpdateFeed, view::setUpdateListComplete));
    }

    private void onErrorChangeFeedUrl(Throwable error){
        view.setUpdateUrlComplete();
        // TODO: show error message
    }

    private void onChangeFeedUrl(String url){
//        addSubscription(rssModel.setFeed(url)
//            .flatMap(aVoid -> rssModel.getUpdateFeed())
//            .subscribe(this::updateFeedList, this::onErrorChangeFeedUrl, view::setUpdateUrlComplete));
    }

}
