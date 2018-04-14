package com.khalturin.dmitriy.presentation.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.databinding.ActivityNewsBinding;
import com.khalturin.dmitriy.presentation.presenter.NewsPresenter;

import static com.khalturin.dmitriy.presentation.view.SupportActionBar.setChildActionBar;

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

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    ActivityNewsBinding binding = DataBindingUtil
      .setContentView(this, R.layout.activity_news);

    NewsPresenter presenter = ViewModelProviders.of(this).get(NewsPresenter.class);

    bindPresenter(binding, presenter);
    setupPresenter(presenter);

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
