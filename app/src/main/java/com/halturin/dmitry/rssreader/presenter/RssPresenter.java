package com.halturin.dmitry.rssreader.presenter;

import rx.Subscription;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com> on 19.02.17 13:39.
 */

public interface RssPresenter {

    void addSubscription(Subscription subscription);

    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();

}
