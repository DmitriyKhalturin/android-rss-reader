package com.halturin.dmitry.rssreader.model.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com> on 21.02.17 23:43.
 */

public final class DatabaseModule {

    private static final String name = "rss.realm";

    public static void initialize(Context context){
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
            .name(name)
            .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static synchronized Realm getInstance(){
        return Realm.getDefaultInstance();
    }

}
