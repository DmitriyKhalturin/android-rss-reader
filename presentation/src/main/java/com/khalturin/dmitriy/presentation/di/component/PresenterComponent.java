package com.khalturin.dmitriy.presentation.di.component;

import com.khalturin.dmitriy.presentation.di.PerPresenter;
import com.khalturin.dmitriy.presentation.di.module.PresenterModule;
import com.khalturin.dmitriy.presentation.view.activity.BookmarksActivity;
import com.khalturin.dmitriy.presentation.view.activity.FeedActivity;
import com.khalturin.dmitriy.presentation.view.activity.NewsActivity;

import dagger.Subcomponent;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 10.04.18 1:10.
 */

@PerPresenter
@Subcomponent(modules = {
  PresenterModule.class
})
public interface PresenterComponent {

  void inject(FeedActivity activity);
  void inject(NewsActivity activity);
  void inject(BookmarksActivity activity);

}
