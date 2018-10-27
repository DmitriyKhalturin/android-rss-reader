package com.khalturin.dmitriy.domain.interactor

import com.khalturin.dmitriy.domain.executor.PostExecutionThread
import com.khalturin.dmitriy.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 24.10.18 21:19.
 */
abstract class UseCase<T, Params>(
  private val mThreadExecutor: ThreadExecutor,
  private val mPostExecutionThread: PostExecutionThread
) {

  abstract fun buildUseCaseObservable(params: Params): Observable<T>

  fun execute(params: Params) = buildUseCaseObservable(params)
    .subscribeOn(Schedulers.from(mThreadExecutor))
    .observeOn(mPostExecutionThread.getScheduler())

  private val mDisposables = CompositeDisposable()

  fun execute(observer: DisposableObserver<T>, params: Params) {
    mDisposables.add(
      execute(params).subscribeWith(observer)
    )
  }

  fun dispose() {
    when (mDisposables.isDisposed) {
      false -> mDisposables.dispose()
    }
  }

}
