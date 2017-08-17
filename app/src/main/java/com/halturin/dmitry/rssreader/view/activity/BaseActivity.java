package com.halturin.dmitry.rssreader.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.halturin.dmitry.rssreader.presenter.RssPresenter;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:20.
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
