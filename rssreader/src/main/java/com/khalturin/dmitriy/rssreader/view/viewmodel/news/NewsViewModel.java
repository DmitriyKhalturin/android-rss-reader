package com.khalturin.dmitriy.rssreader.view.viewmodel.news;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.EMPTY_STRING;
import static com.khalturin.dmitriy.rssreader.view.viewmodel.DefaultFieldValue.FALSE;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 15:43.
 */

public class NewsViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  public Long id = null;

//==================================================================================================
//    Binding ViewModel Members
//==================================================================================================

  public ObservableField<String> title = new ObservableField<>(EMPTY_STRING);
  public ObservableField<String> description = new ObservableField<>(EMPTY_STRING);
  public ObservableField<String> date = new ObservableField<>(EMPTY_STRING);
  // TODO: ...
  public ObservableField<Drawable> image = new ObservableField<>();
  public ObservableField<Boolean> isWasRead = new ObservableField<>(FALSE);

}
