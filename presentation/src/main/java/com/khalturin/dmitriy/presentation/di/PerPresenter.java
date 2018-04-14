package com.khalturin.dmitriy.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 14.04.18 17:48.
 */

@Scope
@Retention(RUNTIME)
public @interface PerPresenter {
}
