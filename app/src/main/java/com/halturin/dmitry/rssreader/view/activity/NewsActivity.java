package com.halturin.dmitry.rssreader.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.NewsPresenter;
import com.halturin.dmitry.rssreader.presenter.vo.News;
import com.halturin.dmitry.rssreader.view.NewsView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 22:00.
 */

public class NewsActivity extends RssActivity implements NewsView {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsActivity(){
        rssPresenter = new NewsPresenter(this);
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

//==================================================================================================
//    Class Implementation NewsView
//==================================================================================================

    @Override
    public void setContent(News news){

    }

}
