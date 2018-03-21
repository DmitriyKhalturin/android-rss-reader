package com.khalturin.dmitriy.presentation;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.02.17 1:33.
 */

public final class SubscribersList<T> {

    private List<Subscriber> list = null;
    private Observable<T> observable = null;

    public SubscribersList(){
        list = new ArrayList<>();

        observable = Observable.create(subscriber -> {
            list.add(subscriber);
        });
    }

    public Observable<T> getObservable(){
        return observable;
    }

    public void onNext(final T value){
        emittedObservable(value, false, null);
    }

    public void onSingle(final T value){
        emittedObservable(value, true, null);
    }

    public void onError(final Throwable throwable){
        emittedObservable(null, null, throwable);
    }

    @SuppressWarnings("unchecked")
    private void emittedObservable(final T value, @Nullable final Boolean complete, final Throwable throwable){
        Iterator<Subscriber> iterator = list.iterator();

        while(iterator.hasNext()){
            Subscriber subscriber = iterator.next();

            if(subscriber != null && !subscriber.isUnsubscribed()){
                if(throwable == null){
                    subscriber.onNext(value);
                    if(complete != null && complete){
                        subscriber.onCompleted();
                    }
                }else{
                    subscriber.onError(throwable);
                }
            }else{
                iterator.remove();
            }
        }
    }

}
