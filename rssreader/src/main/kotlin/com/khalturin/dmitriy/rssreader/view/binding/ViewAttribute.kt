package com.khalturin.dmitriy.rssreader.view.binding

import android.content.Context.INPUT_METHOD_SERVICE
import android.databinding.BindingAdapter
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 04.11.18 23:22.
 */
object ViewAttribute {

  @JvmStatic
  @BindingAdapter("viewClickable")
  fun bindViewClickable(view: View, clickable: Boolean?){
    if(clickable is Boolean){
      view.isFocusable = clickable
      view.isFocusableInTouchMode = clickable
      view.isClickable = clickable
    }
  }

  @JvmStatic
  @BindingAdapter("viewGoneVisibility")
  fun bindViewGoneVisibility(view: View, visibility: Boolean?){
    view.visibility = if (visibility as Boolean) VISIBLE else GONE
  }

  @JvmStatic
  @BindingAdapter("viewInvisibleVisibility")
  fun bindViewInvisibleVisibility(view: View, visibility: Boolean?){
    view.visibility = if (visibility as Boolean) VISIBLE else INVISIBLE
  }

  @JvmStatic
  @BindingAdapter("viewChangeFocus")
  fun bindViewChangeFocus(view: View, active: Boolean?){
    if(active as Boolean){
      view.setOnFocusChangeListener { v, hasFocus ->
        if(!hasFocus){
          val inputMethodManager = view.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

          inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
      }
    }
  }

}
