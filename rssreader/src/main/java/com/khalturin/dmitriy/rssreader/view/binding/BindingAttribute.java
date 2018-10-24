package com.khalturin.dmitriy.rssreader.view.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.khalturin.dmitriy.rssreader.view.layout.FloatingLayout;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.03.18 2:04.
 */

public final class BindingAttribute {

  @BindingAdapter("bind:viewVisibility")
  public static void bindViewVisibility(View view, ObservableField<Boolean> field){
    BindingTransformer.toObservable(field)
      .subscribe(visibility -> {
        view.setVisibility((visibility ? View.VISIBLE : View.GONE));
      });
  }

  @BindingAdapter("bind:hideKeyboard")
  public static void bindHideKeyboard(EditText view, boolean active){
    if(active){
      view.setOnFocusChangeListener((View v, boolean hasFocus) -> {
        if(!hasFocus){
          InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE);

          if(inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
          }
        }
      });
    }
  }

  @BindingAdapter("bind:floatingViewVisibility")
  public static void bindFloatingViewVisibility(View view, ObservableField<Boolean> field){
    FloatingLayout floatingLayout = (FloatingLayout) view;

    BindingTransformer.toObservable(field)
      .subscribe(floatingLayout::setVisibility);
  }

}
