package com.halturin.dmitry.rssreader.presenter;

import com.halturin.dmitry.rssreader.presenter.mapper.FeedMapper;
import com.halturin.dmitry.rssreader.view.FeedView;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:01.
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

        setFeedList();
        setActionListeners();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setFeedList(){
        addSubscription(rssModel.getItemsList()
            .map(mapper)
            .subscribe(view::setList, throwable -> {
                // TODO: processing exception
            }));
    }

    private void setActionListeners(){
        addSubscription(view.getOnUpdateList()
            .subscribe(this::onUpdateFeed));
        addSubscription(view.getOnUpdateUrl()
            .subscribe(this::onChangeFeedUrl));
    }

    private void onUpdateFeed(Void aVoid){
        addSubscription(rssModel.getUpdateFeed()
            .subscribe(aBoolean -> {
                view.setUpdateListComplete();
                setFeedList();
            }, throwable -> {
                // TODO: processing exception
            }));
    }

    private void onChangeFeedUrl(String url){
        addSubscription(rssModel.setFeed(url)
            .flatMap(aVoid -> {
                return rssModel.getUpdateFeed();
            }).subscribe(aBoolean -> {
                view.setUpdateUrlComplete();
                setFeedList();
            }, throwable -> {
                // TODO: processing exception
            }));
    }

}
