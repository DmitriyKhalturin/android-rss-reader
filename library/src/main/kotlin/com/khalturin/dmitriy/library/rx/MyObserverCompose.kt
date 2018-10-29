package com.khalturin.dmitriy.library.rx

import android.os.Handler
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 30.10.18 1:51.
 */
object MyObserverCompose {

  @JvmStatic
  fun <T> applyMainThreadScheduler(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
      observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
    }
  }

  @JvmStatic
  fun <T> applyScheduler(handler: Handler): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
      observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.from(handler.looper))
    }
  }

}
