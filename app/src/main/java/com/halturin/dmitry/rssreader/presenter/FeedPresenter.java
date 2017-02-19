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

        // TODO: implementation observer view actions
    }

}
