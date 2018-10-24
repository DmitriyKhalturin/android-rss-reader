package com.khalturin.dmitriy.rssreader.di.component;

import com.khalturin.dmitriy.rssreader.di.PerPresenter;
import com.khalturin.dmitriy.rssreader.di.module.PresenterModule;
import com.khalturin.dmitriy.rssreader.view.activity.BookmarksActivity;
import com.khalturin.dmitriy.rssreader.view.activity.FeedActivity;
import com.khalturin.dmitriy.rssreader.view.activity.NewsActivity;

import dagger.Component;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 14.04.18 17:48.
 */

@PerPresenter
@Component(modules = {
  PresenterModule.class
})
public interface PresenterComponent {

  void inject(FeedActivity activity);
  void inject(NewsActivity activity);
  void inject(BookmarksActivity activity);

}
