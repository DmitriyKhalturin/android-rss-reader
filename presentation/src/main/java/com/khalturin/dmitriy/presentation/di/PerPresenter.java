package com.khalturin.dmitriy.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 10.04.18 1:08.
 */

@Scope
@Retention(RUNTIME)
public @interface PerPresenter {
}
