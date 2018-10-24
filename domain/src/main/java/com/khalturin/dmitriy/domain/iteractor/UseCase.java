package com.khalturin.dmitriy.domain.iteractor;

import com.khalturin.dmitriy.domain.executor.PostExecutionThread;
import com.khalturin.dmitriy.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 26.04.18 22:12.
 */

public abstract class UseCase<T, Params> {

  private final ThreadExecutor mThreadExecutor;
  private final PostExecutionThread mPostExecutionThread;
  private final CompositeDisposable mDisposables;

  public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
    this.mThreadExecutor = threadExecutor;
    this.mPostExecutionThread = postExecutionThread;
    this.mDisposables = new CompositeDisposable();
  }

  abstract Observable<T> buildUseCaseObservable(Params params);

  public void execute(DisposableObserver<T> observer, Params params){
    if(observer != null){
      Observable<T> observable = buildUseCaseObservable(params)
        .subscribeOn(Schedulers.from(mThreadExecutor))
        .observeOn(mPostExecutionThread.getScheduler());

      addDisposable(
        observable.subscribeWith(observer)
      );
    }
  }

  public Observable<T> execute(Params params){
    return buildUseCaseObservable(params)
      .subscribeOn(Schedulers.from(mThreadExecutor))
      .observeOn(mPostExecutionThread.getScheduler());
  }

  public void dispose(){
    if(!mDisposables.isDisposed()){
      mDisposables.dispose();
    }
  }

  private void addDisposable(Disposable disposable){
    if(disposable != null){
      mDisposables.add(disposable);
    }
  }

}
