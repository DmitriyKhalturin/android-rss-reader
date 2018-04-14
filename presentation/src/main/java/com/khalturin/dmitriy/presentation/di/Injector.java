package com.khalturin.dmitriy.presentation.di;

import android.app.Application;

import com.khalturin.dmitriy.presentation.di.component.AppComponent;
import com.khalturin.dmitriy.presentation.di.component.DaggerAppComponent;
import com.khalturin.dmitriy.presentation.di.component.PresenterComponent;
import com.khalturin.dmitriy.presentation.di.module.ApplicationContextModule;
import com.khalturin.dmitriy.presentation.di.module.NavigatorModule;
import com.khalturin.dmitriy.presentation.di.module.PresenterModule;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 10.04.18 1:26.
 */

public final class Injector {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private static Injector sInstance = new Injector();

  private static AppComponent sAppComponent;
  private static PresenterComponent sPresenterComponent;

//==================================================================================================
//    Class Methods
//==================================================================================================

  public static Injector getInstance(){
    return sInstance;
  }

  public void buildAppComponent(Application application){
    sAppComponent = DaggerAppComponent.builder()
      .applicationContextModule(new ApplicationContextModule(application))
      .navigatorModule(new NavigatorModule())
      .build();
  }

  public AppComponent getAppComponent(){
    return sAppComponent;
  }

  public synchronized PresenterComponent getPresenterComponent(){
    if(sPresenterComponent == null){
      sPresenterComponent = sAppComponent.getPresenterComponent(new PresenterModule());
    }

    return sPresenterComponent;
  }

  public synchronized void clearPresenterComponent(){
    sPresenterComponent = null;
  }

}
