package com.halturin.dmitry.rssreader.app.transformer;

import java.util.Date;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 18.08.17 11:13.
 */

public final class DateToStringTransformer {

    public static String simple(Date date){
        return date.toString();
    }

}
