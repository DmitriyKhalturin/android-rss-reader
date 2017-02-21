package com.halturin.dmitry.rssreader.presenter;

import com.halturin.dmitry.rssreader.view.FeedView;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:01.
 */

public class FeedPresenter extends RssPresenterImpl {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private FeedView view = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedPresenter(FeedView view){
        this.view = view;
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void onResume(){
        super.onResume();

        addSubscription(rssModel.getFeed().subscribe(view::setList));
        addSubscription(view.getOnUpdateList().subscribe(this::onUpdateList));
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void onUpdateList(Void _aVoid){
        addSubscription(rssModel.updateFeed()
            .subscribe(aVoid -> {
                view.setUpdateListComplete();
            }, error -> {
                // TODO: processing error
            })
        );
    }

}
