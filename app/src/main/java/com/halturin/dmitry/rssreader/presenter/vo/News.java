package com.halturin.dmitry.rssreader.presenter.vo;

import android.graphics.Bitmap;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 13:40.
 */

public class News {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private long id;
    private String title;
    private String description;
    private String link;
    private Bitmap image;
    private String date;

    private boolean isReaded;

//==================================================================================================
//    Class Methods
//==================================================================================================

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public boolean isReaded(){
        return isReaded;
    }

    public void setReaded(boolean readed){
        isReaded = readed;
    }

}
