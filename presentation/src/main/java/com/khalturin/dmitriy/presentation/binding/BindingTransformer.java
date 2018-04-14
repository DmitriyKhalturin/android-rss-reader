package com.khalturin.dmitriy.presentation.binding;

import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableField;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.03.18 13:17.
 */

public final class BindingTransformer {

  @SuppressWarnings("unchecked")
  public static <T> Observable<T> toObservable(ObservableField<T> field){
    return Observable.create(emitter -> {
      FieldChanged fieldChanged = new FieldChanged(field, emitter);

      emitter.setCancellable(() -> {
        field.removeOnPropertyChangedCallback(fieldChanged);
      });

      field.addOnPropertyChangedCallback(fieldChanged);

      emitter.onNext(field.get());
    });
  }

  public static <T> ObservableField<T> toField(Observable<T> observable){
    ObservableField<T> field = new ObservableField<>();

    observable.subscribe(field::set);

    return field;
  }

  private static final class FieldChanged<T> extends OnPropertyChangedCallback {
    private ObservableField<T> field;
    private ObservableEmitter<T> emitter;

    public FieldChanged(ObservableField<T> field, ObservableEmitter<T> emitter){
      this.field = field;
      this.emitter = emitter;
    }

    @Override
    public void onPropertyChanged(android.databinding.Observable observable, int i) {
      emitter.onNext(field.get());
    }

  }

}
