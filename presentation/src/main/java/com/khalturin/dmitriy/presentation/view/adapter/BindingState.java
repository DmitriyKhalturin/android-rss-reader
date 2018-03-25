package com.khalturin.dmitriy.presentation.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.03.18 2:04.
 */

public class BindingState {

  @BindingAdapter("bind:viewVisibility")
  public static void bindVisibility(View view, boolean visible){
    view.setVisibility((visible ? View.GONE : View.VISIBLE));
  }

}
