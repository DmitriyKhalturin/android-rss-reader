package com.khalturin.dmitriy.presentation.di.component;

import com.khalturin.dmitriy.presentation.di.module.ApplicationModule;
import com.khalturin.dmitriy.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 22.03.18 17:52.
 */

@Component(modules = {
  ApplicationModule.class
})
@Singleton
public interface ApplicationComponent {
}
