package com.khalturin.dmitriy.presentation.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.databinding.ActivityNewsBinding;
import com.khalturin.dmitriy.presentation.presenter.NewsPresenter;
import com.khalturin.dmitriy.presentation.view.NewsView;
import com.khalturin.dmitriy.presentation.viewmodel.news.NewsViewModel;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 22:00.
 */

public class NewsActivity extends BaseActivity implements NewsView {

//==================================================================================================
//    Class Variables
//==================================================================================================

  public static final String NEWS_ID = "NEWS_ID";
  public static final long DEFAULT_NEWS_ID = -1;

  private long newsId;

  private NewsViewModel mNewsViewModel = new NewsViewModel();

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
    ActivityNewsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

    binding.setNewsViewModel(mNewsViewModel);

    setNewsId();

    setSupportActionBar(findViewById(R.id.toolbar));

    ActionBar actionBar = getSupportActionBar();

    if(actionBar != null){
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
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
  public void setContent(NewsViewModel news){
    mNewsViewModel = news;
  }

}
