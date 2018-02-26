package com.halturin.dmitry.rssreader.presenter.vo;

import com.halturin.dmitry.rssreader.app.transformer.DateToStringTransformer;
import com.halturin.dmitry.rssreader.model.dto.ItemEntity;

import java.util.Date;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 13:40.
 */

public class News {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private long id;
    private String author;
    private String title;
    private String description;
    private String link;
    private String image;
    private String date;

    private boolean isReaded;

    private boolean isFavorite;

//==================================================================================================
//    Class Methods
//==================================================================================================

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
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

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = DateToStringTransformer.simple(date);
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

    public boolean isFavorite(){
        return isFavorite;
    }

    public void setFavorite(boolean favorite){
        isFavorite = favorite;
    }

//==================================================================================================
//    Class Specific Methods
//==================================================================================================

    public void set(ItemEntity entity){
        setId(entity.getId());
        setAuthor(entity.getAuthor());
        setTitle(entity.getTitle());
        setDescription(entity.getDescription());
        setLink(entity.getLink());
        setImage(entity.getImage());
        setDate(entity.getDate());
        setReaded(entity.isReaded());
        setFavorite(entity.isFavorite());
    }

}
