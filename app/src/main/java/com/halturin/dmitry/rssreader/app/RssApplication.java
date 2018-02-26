package com.halturin.dmitry.rssreader.app;

import android.app.Application;

import com.halturin.dmitry.rssreader.model.database.DatabaseModule;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 21.02.17 23:45.
 */

public class RssApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        DatabaseModule.initialize(this);
    }

}
