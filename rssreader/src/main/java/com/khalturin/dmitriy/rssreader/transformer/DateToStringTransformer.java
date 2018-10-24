package com.khalturin.dmitriy.rssreader.transformer;

import java.util.Date;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 13:40.
 */

public final class DateToStringTransformer {

    private static final String emptyString = "";

    public static String simple(Date date){
        return (date != null ? date.toString() : emptyString);
    }

}
