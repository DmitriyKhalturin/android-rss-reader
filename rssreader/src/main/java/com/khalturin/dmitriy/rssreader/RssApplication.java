package com.khalturin.dmitriy.rssreader;

import android.app.Application;

import com.khalturin.dmitriy.rssreader.di.Injector;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 21.02.17 23:45.
 */

public class RssApplication extends Application {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private static Injector sInjector = Injector.getInstance();

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  public void onCreate(){
    super.onCreate();

    sInjector.buildAppComponent(this);
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  public static Injector getInjector(){
    return sInjector;
  }

}
