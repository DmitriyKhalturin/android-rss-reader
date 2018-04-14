package com.khalturin.dmitriy.presentation.di;

import android.app.Application;
import android.support.v4.app.FragmentActivity;

import com.khalturin.dmitriy.presentation.di.component.AppComponent;
import com.khalturin.dmitriy.presentation.di.component.DaggerAppComponent;
import com.khalturin.dmitriy.presentation.di.component.DaggerPresenterComponent;
import com.khalturin.dmitriy.presentation.di.component.PresenterComponent;
import com.khalturin.dmitriy.presentation.di.module.AppModule;
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

//==================================================================================================
//    Class Methods
//==================================================================================================

  public static Injector getInstance(){
    return sInstance;
  }

  public void buildAppComponent(Application application){
    sAppComponent = DaggerAppComponent.builder()
      .appModule(new AppModule(application))
      .navigatorModule(new NavigatorModule())
      .build();
  }

  public AppComponent getAppComponent(){
    return sAppComponent;
  }

  public PresenterComponent getPresenterComponent(FragmentActivity activity){
    return DaggerPresenterComponent.builder()
      .presenterModule(new PresenterModule(activity))
      .build();
  }

}
