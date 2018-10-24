package com.khalturin.dmitriy.rssreader.view.viewmodel.feed;

import android.databinding.ObservableField;

import com.khalturin.dmitriy.rssreader.R;
import com.khalturin.dmitriy.rssreader.view.binding.BindingTransformer;

import io.reactivex.Observable;

import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.FALSE;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 29.03.18 23:04.
 */

public class SwipeRefreshViewModel {

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<Boolean> isRefreshing = new ObservableField<>(FALSE);
  public int[] colorScheme = {
    R.color.colorRefreshOne,
    R.color.colorRefreshTwo,
    R.color.colorRefreshThree
  };

  public void updateFeedItems(){
    // variable isn't updating directly
    isRefreshing.set(true);
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  public Observable<Boolean> getOnUpdateFeedItems(){
    return BindingTransformer.toObservable(isRefreshing)
      .filter(aBoolean -> aBoolean);
  }

}
