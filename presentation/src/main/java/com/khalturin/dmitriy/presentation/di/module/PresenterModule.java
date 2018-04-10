package com.khalturin.dmitriy.presentation.di.module;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

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
  public FeedPresenter providerFeedPresenter(Application application){
    return getPresenter(application, FeedPresenter.class);
  }

  @PerPresenter
  @Provides
  public NewsPresenter providerNewsPresenter(Application application){
    return getPresenter(application, NewsPresenter.class);
  }

  @PerPresenter
  @Provides
  public BookmarksPresenter providerBookmarksPresenter(Application application){
    return getPresenter(application, BookmarksPresenter.class);
  }

  @SuppressWarnings("unchecked")
  private <T extends ViewModel> T getPresenter(Application application, Class<T> clazz){
    return ViewModelProvider.AndroidViewModelFactory
      .getInstance(application).create(clazz);
  }

}
