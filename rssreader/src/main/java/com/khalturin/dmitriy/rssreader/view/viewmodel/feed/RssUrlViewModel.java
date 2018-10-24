package com.khalturin.dmitriy.rssreader.view.viewmodel.feed;

import android.databinding.ObservableField;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static android.support.v4.util.PatternsCompat.WEB_URL;
import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.EMPTY_STRING;
import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.FALSE;
import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.TRUE;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.02.18 1:15.
 */

public class RssUrlViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private PublishSubject<String> onUpdateRssUrl = PublishSubject.create();

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<Boolean> isVisible = new ObservableField<>(FALSE);
  public ObservableField<String> rssUrl = new ObservableField<>(EMPTY_STRING);
  public ObservableField<Boolean> isListening = new ObservableField<>(TRUE);
  public ObservableField<Boolean> isLoading = new ObservableField<>(FALSE);

  public void setRssUrl(View view){
    String url = rssUrl.get();

    if(urlValidation(url)){
      setLoaderVisible(true);
      onUpdateRssUrl.onNext(url);
    }
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  public void changeLayoutVisibility(){
    isVisible.set(!isVisible.get());
  }

  public Observable<String> getOnUpdateRssUrl(){
    return onUpdateRssUrl;
  }

  public void setUpdateRssUrlComplete(){
    setLoaderVisible(false);
  }

  private void setLoaderVisible(boolean visible){
    isListening.set(!visible);
    isLoading.set(visible);
  }

  private boolean urlValidation(String url){
    return WEB_URL.matcher(url).matches();
  }

}
