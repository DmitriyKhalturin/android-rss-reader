package com.khalturin.dmitriy.presentation.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.khalturin.dmitriy.presentation.binding.BindingConverter;

import io.reactivex.Observable;

import static com.khalturin.dmitriy.presentation.viewmodel.DefaultFieldValue.EMPTY_STRING;
import static com.khalturin.dmitriy.presentation.viewmodel.DefaultFieldValue.FALSE;
import static com.khalturin.dmitriy.presentation.viewmodel.DefaultFieldValue.TRUE;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.02.18 1:15.
 */

public class RssUrlViewModel {

  public ObservableField<String> url = new ObservableField<>(EMPTY_STRING);
  public ObservableField<Boolean> isListening = new ObservableField<>(TRUE);
  public ObservableField<Boolean> isLoading = new ObservableField<>(FALSE);

  public void setUrl(View view){
  }

  private void setLoaderVisible(boolean visible){
    isListening.set(!visible);
    isLoading.set(visible);
  }

  public Observable<String> getOnUpdate(){
    return BindingConverter.toObservable(url)
      .filter(this::urlValidation)
      .map(url -> {
        setLoaderVisible(true);
        return url;
      });
  }

  public void setUpdateComplete(){
    setLoaderVisible(false);
  }

  private boolean urlValidation(String url){
    return !url.isEmpty();
  }

}
