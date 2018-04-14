package com.khalturin.dmitriy.presentation.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 14.04.18 12:34.
 */

public final class SupportActionBar {

  public static void setRootActionBar(AppCompatActivity activity, int id){
    activity.setSupportActionBar(activity.findViewById(id));
  }

  public static void setChildActionBar(AppCompatActivity activity, int id){
    activity.setSupportActionBar(activity.findViewById(id));
    ActionBar actionBar = activity.getSupportActionBar();

    if(actionBar != null){
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

}
