package com.khalturin.dmitriy.presentation.viewmodel;

import android.databinding.ObservableField;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.02.18 1:15.
 */

public class RssUrlViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  public ObservableField<String> rssUrl = new ObservableField<>();
  public ObservableField<Boolean> isReady = new ObservableField<>();



  private PublishSubject<String> onUpdateUrl = PublishSubject.create();

//  private boolean mReady = true;

  private View mButton;
  private View mInput;
  private View mLoader;
  private View mIcon;

//==================================================================================================
//    Class Constructor
//==================================================================================================

  public RssUrlViewModel(){
//    this.mButton = mButton;
//    this.mInput = mInput;
//    this.mLoader = mLoader;
//    this.mIcon = mIcon;
//
//    setButtonListener();
//    setLoaderVisible(false);
//    setInputListener(null);
  }

//==================================================================================================
//    Class Methods
//==================================================================================================

  public void onSetRssUrl(View view){
    int i = 1 + 1;
  }

  private void setButtonListener(){
    RxView.clicks(mButton).subscribe(this::onClickButton);
  }

  private void setLoaderVisible(boolean visible){
    if(visible){
      mLoader.setVisibility(VISIBLE);
      mIcon.setVisibility(GONE);
    }else{
      mLoader.setVisibility(GONE);
      mIcon.setVisibility(VISIBLE);
    }
  }

//  private void setInputListener(Context context){
//    ((EditText) mInput).setOnFocusChangeListener((View v, boolean hasFocus) -> {
//      if(!hasFocus){
//        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        if(inputMethodManager != null){
//          inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        }
//      }
//    });
//  }

  private boolean urlValidation(String url){
    return !url.isEmpty();
  }

  private void onClickButton(Void aVoid){
    String url = ((EditText) mInput).getText().toString();

    if(/*mReady &&*/ urlValidation(url)){
//      mReady = false;
      setLoaderVisible(true);
      onUpdateUrl.onNext(url);
    }
  }

  public Observable<String> getOnUpdate(){
    return onUpdateUrl.asObservable();
  }

  public void setUpdateComplete(){
    setLoaderVisible(false);
//    mReady = true;
  }

}
