package com.khalturin.dmitriy.presentation.binding.recycler.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 23:25.
 */

public class BindingViewHolder extends RecyclerView.ViewHolder {

  private ViewDataBinding mBinding;

  public BindingViewHolder(View view){
    super(view);

    mBinding = DataBindingUtil.bind(view);
  }

  public ViewDataBinding getBinding(){
    return mBinding;
  }

}
