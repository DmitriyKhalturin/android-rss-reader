package com.khalturin.dmitriy.presentation.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:09.
 */

public class RssPresenterImpl implements RssPresenter {

//==================================================================================================
//    Class Variables
//==================================================================================================

//    protected RssModel rssModel = null;

    private CompositeSubscription compositeSubscription;

//==================================================================================================
//    Class Implementation RssPresenter
//==================================================================================================

    @Override
    public void addSubscription(Subscription subscription){
        compositeSubscription.add(subscription);
    }

    @Override
    public void onCreate(){
//        rssModel = new RssModelImpl();
    }

    @Override
    public void onResume(){
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onPause(){
        if(compositeSubscription != null){
            compositeSubscription.clear();
            compositeSubscription = null;
        }
    }

    @Override
    public void onDestroy(){
//        rssModel = null;
    }

}
