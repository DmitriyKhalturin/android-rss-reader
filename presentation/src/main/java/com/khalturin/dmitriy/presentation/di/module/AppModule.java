package com.khalturin.dmitriy.presentation.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 22.03.18 17:53.
 */

@Module
public class AppModule {

  private Application mApplication;

  public AppModule(Application mApplication){
    this.mApplication = mApplication;
  }

  @Singleton
  @Provides
  public Application provideApplication(){
    return this.mApplication;
  }

  @Singleton
  @Provides
  public Context provideContext(){
    return this.mApplication;
  }

}
