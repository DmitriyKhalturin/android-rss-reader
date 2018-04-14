package com.khalturin.dmitriy.presentation.binding.recycler;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 14.04.18 22:19.
 */

public final class BindingAttribute {

  @BindingAdapter("bind:recyclerManager")
  public static void bindRecyclerManager(RecyclerView recyclerView, RecyclerManager recyclerManager){
    recyclerView.setLayoutManager(recyclerManager.getLayoutManager());
    recyclerView.setItemAnimator(recyclerManager.getItemAnimator());
    recyclerView.setAdapter(recyclerManager.getAdapter());
  }

}
