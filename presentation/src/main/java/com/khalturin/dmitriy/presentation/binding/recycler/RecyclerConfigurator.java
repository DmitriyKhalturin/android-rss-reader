package com.khalturin.dmitriy.presentation.binding.recycler;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.03.18 16:02.
 */

public final class RecyclerConfigurator {

  private RecyclerView.LayoutManager mLayoutManager;
  private RecyclerView.ItemAnimator mItemAnimator;
  private RecyclerView.Adapter mAdapter;


  public RecyclerView.LayoutManager getLayoutManager() {
    return mLayoutManager;
  }

  public void setLayoutManager(RecyclerView.LayoutManager mLayoutManager) {
    this.mLayoutManager = mLayoutManager;
  }

  public RecyclerView.ItemAnimator getItemAnimator() {
    return mItemAnimator;
  }

  public void setItemAnimator(RecyclerView.ItemAnimator mItemAnimator) {
    this.mItemAnimator = mItemAnimator;
  }

  public RecyclerView.Adapter getAdapter() {
    return mAdapter;
  }

  public void setAdapter(RecyclerView.Adapter mAdapter) {
    this.mAdapter = mAdapter;
  }

}