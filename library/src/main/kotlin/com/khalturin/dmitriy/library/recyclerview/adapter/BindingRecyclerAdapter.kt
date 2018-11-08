package com.khalturin.dmitriy.library.recyclerview.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject
import java.util.ArrayList

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 01.11.18 23:01.
 */
class BindingRecyclerAdapter<T>(
  @LayoutRes private val mLayoutId: Int,
  mInitItems: MutableList<T>?,
  private val mVariableId: Int
) : RecyclerView.Adapter<BindingViewHolder>() {

  private val mItems = mInitItems ?: ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BindingViewHolder(
    LayoutInflater.from(parent.context).inflate(mLayoutId, parent, false)
  )

  override fun getItemCount() =  mItems.size

  override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
    holder.mBinding?.setVariable(mVariableId, mItems[position])
  }

  fun getItems() = mItems

  fun setItems(items: MutableList<T>?) {
    mItems.clear()
    mItems.addAll(items ?: ArrayList())

    notifyDataSetChanged()

    mSubjectItemsChanged.onNext(mItems.size)
  }

  private val mSubjectItemsChanged = PublishSubject.create<Int>()

  fun getOnItemsChanged() = mSubjectItemsChanged

}
