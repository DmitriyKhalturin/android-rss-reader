package com.khalturin.dmitriy.presentation.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerConfigurator;
import com.khalturin.dmitriy.presentation.view.layout.FloatingLayout;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.03.18 2:04.
 */

public final class BindingAttribute {

  @BindingAdapter("bind:recyclerConfigurator")
  public static void bindRecyclerConfigurator(RecyclerView recyclerView, RecyclerConfigurator recyclerConfigurator){
    recyclerView.setLayoutManager(recyclerConfigurator.getLayoutManager());
    recyclerView.setItemAnimator(recyclerConfigurator.getItemAnimator());
    recyclerView.setAdapter(recyclerConfigurator.getAdapter());
  }

  @BindingAdapter("bind:viewVisibility")
  public static void bindViewVisibility(View view, ObservableField<Boolean> field){
    BindingConverter.toObservable(field)
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
    view.setVisibility(View.GONE);

    BindingConverter.toObservable(field)
      .subscribe(visibility -> {
        FloatingLayout floatingLayout = (FloatingLayout) view;

        if(visibility){
          floatingLayout.setVisible();
        }else{
          floatingLayout.setInvisible();
        }
      });
  }

}
