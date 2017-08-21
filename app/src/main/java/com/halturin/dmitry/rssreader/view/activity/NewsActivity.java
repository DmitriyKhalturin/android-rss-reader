package com.halturin.dmitry.rssreader.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.NewsPresenter;
import com.halturin.dmitry.rssreader.presenter.vo.News;
import com.halturin.dmitry.rssreader.view.NewsView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 22:00.
 */

public class NewsActivity extends BaseActivity implements NewsView {

//==================================================================================================
//    Class Variables
//==================================================================================================

    public static final String NEWS_ID = "NEWS_ID";
    public static final long DEFAULT_NEWS_ID = -1;

    private long newsId;

    @BindView(R.id.toolbar)
    protected Toolbar toolbarView;

    @BindView(R.id.news_title)
    protected TextView titleView;

    @BindView(R.id.news_date)
    protected TextView dateView;

    @BindView(R.id.news_image)
    protected ImageView imageView;

    @BindView(R.id.news_description)
    protected TextView descriptionView;

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

        setSupportActionBar(toolbarView);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setNewsId();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setNewsId(){
        Intent intent = getIntent();
        newsId = intent.getLongExtra(NEWS_ID, DEFAULT_NEWS_ID);
    }

//==================================================================================================
//    Class Implementation NewsView
//==================================================================================================

    @Override
    public long getNewsId(){
        return newsId;
    }

    @Override
    public void setContent(News news){
        if(news != null){
            titleView.setText(news.getTitle());
            dateView.setText(news.getDate());
            // imageView.setImageBitmap(news.getImage());
            descriptionView.setText(news.getDescription());
        }
    }

}
