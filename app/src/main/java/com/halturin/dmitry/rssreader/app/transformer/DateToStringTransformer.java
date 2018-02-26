package com.halturin.dmitry.rssreader.app.transformer;

import java.util.Date;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com> on 18.08.17 11:13.
 */

public final class DateToStringTransformer {

    private static final String emptyString = "";

    public static String simple(Date date){
        return (date != null ? date.toString() : emptyString);
    }

}
