package com.khalturin.dmitriy.presentation;

import android.app.Application;

import com.khalturin.dmitriy.presentation.di.component.ApplicationComponent;
import com.khalturin.dmitriy.presentation.di.component.DaggerApplicationComponent;
import com.khalturin.dmitriy.presentation.di.module.ApplicationModule;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 21.02.17 23:45.
 */

public class RssApplication extends Application {

  private ApplicationComponent sApplicationComponent;

  @Override
  public void onCreate(){
    super.onCreate();
    sApplicationComponent = buildApplicationComponent();
  }

  public ApplicationComponent getApplicationComponent(){
    return sApplicationComponent;
  }

  protected ApplicationComponent buildApplicationComponent(){
    return DaggerApplicationComponent.builder()
      .applicationModule(new ApplicationModule(this))
      .build();
  }

}
