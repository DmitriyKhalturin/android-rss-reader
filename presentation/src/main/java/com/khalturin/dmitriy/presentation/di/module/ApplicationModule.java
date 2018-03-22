package com.khalturin.dmitriy.presentation.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 22.03.18 17:53.
 */

@Module
public class ApplicationModule {

  private Context mApplicationContext;

  public ApplicationModule(Context mApplicationContext){
    this.mApplicationContext = mApplicationContext;
  }

  @Provides
  @Singleton
  Context provideContext(){
    return this.mApplicationContext;
  }

}
