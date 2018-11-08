package com.khalturin.dmitriy.library.recyclerview

import android.support.v7.widget.RecyclerView
import com.khalturin.dmitriy.library.recyclerview.adapter.BindingRecyclerAdapter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 01.11.18 22:59.
 */
data class RecyclerViewManager (
  var mLayoutManager: RecyclerView.LayoutManager,
  var mItemAnimator: RecyclerView.ItemAnimator,
  var mAdapter: BindingRecyclerAdapter<*>
)
