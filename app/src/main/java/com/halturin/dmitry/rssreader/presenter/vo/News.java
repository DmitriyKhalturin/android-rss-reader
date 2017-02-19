package com.halturin.dmitry.rssreader.presenter.vo;

import android.graphics.Bitmap;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 13:40.
 */

public class News {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private String title;
    private String date;
    private Bitmap image;
    private String text;

//==================================================================================================
//    Class Methods
//==================================================================================================

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

}
