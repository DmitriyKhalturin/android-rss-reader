package com.khalturin.dmitriy.library.recyclerview.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.khalturin.dmitriy.library.recyclerview.RecyclerViewManager

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 01.11.18 22:59.
 */
object RecyclerViewAttribute {

  @JvmStatic
  @BindingAdapter("recyclerViewManager")
  fun bindRecyclerViewManager(recyclerView: RecyclerView, recyclerViewManager: RecyclerViewManager<*>?){
    if(recyclerViewManager is RecyclerViewManager){
      recyclerView.layoutManager = recyclerViewManager.mLayoutManager
      recyclerView.itemAnimator = recyclerViewManager.mItemAnimator
      recyclerView.adapter = recyclerViewManager.mAdapter
    }
  }

}
