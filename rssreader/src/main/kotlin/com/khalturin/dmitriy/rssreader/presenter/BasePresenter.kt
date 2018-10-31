package com.khalturin.dmitriy.rssreader.presenter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 31.10.18 0:48.
 */
abstract class BasePresenter<T> constructor(clazz: Class<T>) : ViewModel() {

  protected val mViewModel: T = clazz.newInstance()
  private val mLiveData: MutableLiveData<T> by lazy {
    buildViewModel()
    buildLiveData()
  }

  private fun buildLiveData(): MutableLiveData<T> {
    val data = MutableLiveData<T>()

    data.value = mViewModel

    return data
  }

  fun getLiveData() = mLiveData

  protected abstract fun buildViewModel()

}
