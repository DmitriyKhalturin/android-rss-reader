package com.khalturin.dmitriy.presentation.view.state;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:19.
 */

public class FeedState extends ViewModel {

  public ObservableField<List<NewsState>> list = new ObservableField<>();

}
