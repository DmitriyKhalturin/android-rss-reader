package com.khalturin.dmitriy.library.recyclerview.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 01.11.18 23:00.
 */
class BindingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

  val mBinding: ViewDataBinding? = DataBindingUtil.bind(view)

}
