package com.khalturin.dmitriy.presentation.viewmodel.bookmark;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

import static com.khalturin.dmitriy.presentation.viewmodel.DefaultFieldValue.EMPTY_STRING;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 03.04.18 9:59.
 */

public class FeedViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  public Long id = null;

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<String> url = new ObservableField<>(EMPTY_STRING);
  public ObservableField<String> title = new ObservableField<>(EMPTY_STRING);
  public ObservableField<String> description = new ObservableField<>(EMPTY_STRING);
  public ObservableField<String> author = new ObservableField<>(EMPTY_STRING);
  public ObservableField<String> lastUpdate = new ObservableField<>(EMPTY_STRING);
  // TODO: ...
  public ObservableField<Drawable> image = new ObservableField<>();

}
