package com.khalturin.dmitriy.presentation.di.component;

import com.khalturin.dmitriy.presentation.di.module.ApplicationContextModule;
import com.khalturin.dmitriy.presentation.di.module.NavigatorModule;
import com.khalturin.dmitriy.presentation.presenter.FeedPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 22.03.18 17:52.
 */

@Singleton
@Component(modules = {
  ApplicationContextModule.class, NavigatorModule.class
})
public interface AppComponent {

  void inject(FeedPresenter presenter);

}
