package com.khalturin.dmitriy.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.khalturin.dmitriy.presentation.presenter.RssPresenter;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:20.
 */

public class BaseActivity extends AppCompatActivity {

//==================================================================================================
//    Class Variables
//==================================================================================================

    protected RssPresenter rssPresenter = null;

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        rssPresenter.onCreate();
    }

    @Override
    protected void onResume(){
        super.onResume();

        rssPresenter.onResume();
    }

    @Override
    protected void onPause(){
        rssPresenter.onPause();

        super.onPause();
    }

    @Override
    protected void onDestroy(){
        rssPresenter.onDestroy();
        rssPresenter = null;

        super.onDestroy();
    }

}
