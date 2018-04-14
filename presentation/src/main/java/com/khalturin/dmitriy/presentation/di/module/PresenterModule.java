package com.khalturin.dmitriy.presentation.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.khalturin.dmitriy.presentation.di.PerPresenter;
import com.khalturin.dmitriy.presentation.presenter.BookmarksPresenter;
import com.khalturin.dmitriy.presentation.presenter.FeedPresenter;
import com.khalturin.dmitriy.presentation.presenter.NewsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 10.04.18 0:49.
 */

@Module
public class PresenterModule {

  @PerPresenter
  @Provides
  public FeedPresenter providerFeedPresenter(FragmentActivity activity){
    return ViewModelProviders.of(activity).get(FeedPresenter.class);
  }

  @PerPresenter
  @Provides
  public NewsPresenter providerNewsPresenter(FragmentActivity activity){
    return ViewModelProviders.of(activity).get(NewsPresenter.class);
  }

  @PerPresenter
  @Provides
  public BookmarksPresenter providerBookmarksPresenter(FragmentActivity activity){
    return ViewModelProviders.of(activity).get(BookmarksPresenter.class);
  }

}
