package com.halturin.dmitry.rssreader.view.layout;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 26.02.18 1:15.
 */

public class RssUrlSetter {

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

    public RssUrlSetter(View button, View input, View loader, View icon){
        this.button = button;
        this.input = input;
        this.loader = loader;
        this.icon = icon;

        setButtonListener();
        setLoaderVisible(false);
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

    public Observable<String> getOnUpdateRssUrl(){
        return onUpdateUrl.asObservable();
    }

    public void setUpdateRssUrlComplete(){
        setLoaderVisible(false);
        ready = true;
    }

}
