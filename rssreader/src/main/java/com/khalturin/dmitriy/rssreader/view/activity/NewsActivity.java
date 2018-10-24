package com.khalturin.dmitriy.rssreader.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.khalturin.dmitriy.rssreader.R;
import com.khalturin.dmitriy.rssreader.RssApplication;
import com.khalturin.dmitriy.rssreader.databinding.ActivityNewsBinding;
import com.khalturin.dmitriy.rssreader.presenter.NewsPresenter;

import javax.inject.Inject;

import static com.khalturin.dmitriy.rssreader.view.util.SupportActionBar.setChildActionBar;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 22:00.
 */

public class NewsActivity extends AppCompatActivity {

//==================================================================================================
//    Class Variables
//==================================================================================================

  public static final String NEWS_ID = "NEWS_ID";
  public static final long DEFAULT_NEWS_ID = -1;

  @Inject
  NewsPresenter mPresenter;

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityNewsBinding binding = DataBindingUtil
      .setContentView(this, R.layout.activity_news);

    RssApplication.getInjector()
      .getPresenterComponent(this)
      .inject(this);

    bindPresenter(binding, mPresenter);
    setupPresenter(mPresenter);

    setChildActionBar(this, R.id.toolbar);
  }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  private void bindPresenter(ActivityNewsBinding binding, NewsPresenter presenter){
    presenter.getNewsObserver()
      .observe(this, binding::setNewsViewModel);
  }

  private void setupPresenter(NewsPresenter presenter){
    Intent intent = getIntent();
    long newsId = intent.getLongExtra(NEWS_ID, DEFAULT_NEWS_ID);

    presenter.setNewsId(newsId);
  }

}
