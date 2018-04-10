package com.khalturin.dmitriy.presentation.di.module;

import android.content.Context;

import com.khalturin.dmitriy.presentation.navigator.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 09.04.18 19:13.
 */

@Module
public class NavigatorModule {

  @Singleton
  @Provides
  public Navigator provideNavigator(Context context){
    return new Navigator(context);
  }

}
