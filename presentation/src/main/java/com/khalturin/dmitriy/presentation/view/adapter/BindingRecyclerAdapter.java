package com.khalturin.dmitriy.presentation.view.adapter;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:39.
 */

public class BindingRecyclerAdapter {

  @BindingAdapter("bind:recyclerConfiguration")
  public static void bindRecyclerConfiguration(@NonNull RecyclerView recyclerView, List<?> tmp){
  }

}
