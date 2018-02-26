package com.halturin.dmitry.rssreader.view.layout;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class RssUrlGetter {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private PublishSubject<String> onUpdateUrl = PublishSubject.create();

    private boolean ready = true;

    private View button;
    private View input;
    private View loader;
    private View icon;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public RssUrlGetter(Context context, View button, View input, View loader, View icon){
        this.button = button;
        this.input = input;
        this.loader = loader;
        this.icon = icon;

        setButtonListener();
        setLoaderVisible(false);
        setInputListener(context);
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setButtonListener(){
        RxView.clicks(button).subscribe(this::onClickButton);
    }

    private void setLoaderVisible(boolean visible){
        if(visible){
            loader.setVisibility(VISIBLE);
            icon.setVisibility(GONE);
        }else{
            loader.setVisibility(GONE);
            icon.setVisibility(VISIBLE);
        }
    }

    private void setInputListener(Context context){
        ((EditText) input).setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if(!hasFocus){
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

                if(inputMethodManager != null){
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

    private boolean urlValidation(String url){
        return !url.isEmpty();
    }

    private void onClickButton(Void aVoid){
        String url = ((EditText) input).getText().toString();

        if(ready && urlValidation(url)){
            ready = false;
            setLoaderVisible(true);
            onUpdateUrl.onNext(url);
        }
    }

    public Observable<String> getOnUpdate(){
        return onUpdateUrl.asObservable();
    }

    public void setUpdateComplete(){
        setLoaderVisible(false);
        ready = true;
    }

}
