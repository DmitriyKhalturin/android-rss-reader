package com.khalturin.dmitriy.rssreader.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.khalturin.dmitriy.rssreader.di.PerPresenter;
import com.khalturin.dmitriy.rssreader.presenter.BookmarksPresenter;
import com.khalturin.dmitriy.rssreader.presenter.FeedPresenter;
import com.khalturin.dmitriy.rssreader.presenter.NewsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 14.04.18 17:46.
 */

@Module
public class PresenterModule {

  private FragmentActivity mActivity;

  public PresenterModule(FragmentActivity mActivity){
    this.mActivity = mActivity;
  }

  @PerPresenter
  @Provides
  public FeedPresenter providerFeedPresenter(){
    return ViewModelProviders.of(mActivity).get(FeedPresenter.class);
  }

  @PerPresenter
  @Provides
  public NewsPresenter providerNewsPresenter(){
    return ViewModelProviders.of(mActivity).get(NewsPresenter.class);
  }

  @PerPresenter
  @Provides
  public BookmarksPresenter providerBookmarksPresenter(){
    return ViewModelProviders.of(mActivity).get(BookmarksPresenter.class);
  }

}
