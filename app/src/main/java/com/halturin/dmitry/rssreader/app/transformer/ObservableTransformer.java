package com.halturin.dmitry.rssreader.app.transformer;

import android.os.Handler;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 22.02.17 2:23.
 */

public final class ObservableTransformer {

    @SuppressWarnings("unchecked")
    public static <T, R> Observable.Transformer<T, R> applySchedulers(){
        Observable.Transformer schedulersTransformer = (o) -> ((Observable) o)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

        return (Observable.Transformer<T, R>) schedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Observable.Transformer<T, R> applySchedulers(final Handler handler){
        Observable.Transformer schedulersTransformer = (o) -> ((Observable) o)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.from(handler.getLooper()));

        return (Observable.Transformer<T, R>) schedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Observable.Transformer<T, R> applyIOSchedulers(){
        Observable.Transformer schedulersTransformer = (o) -> ((Observable) o)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io());

        return (Observable.Transformer<T, R>) schedulersTransformer;
    }

}
