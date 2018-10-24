package com.khalturin.dmitriy.rssreader.di.component;

import com.khalturin.dmitriy.rssreader.di.module.AppModule;
import com.khalturin.dmitriy.rssreader.di.module.NavigatorModule;
import com.khalturin.dmitriy.rssreader.presenter.FeedPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 22.03.18 17:52.
 */

@Singleton
@Component(modules = {
  AppModule.class, NavigatorModule.class
})
public interface AppComponent {

  void inject(FeedPresenter presenter);

}
