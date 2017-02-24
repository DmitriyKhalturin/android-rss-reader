package com.halturin.dmitry.rssreader.model.dto;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 22.02.17 1:26.
 */

public class ItemEntity extends RealmObject {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @PrimaryKey
    private long id;

    private long feedId;

    private String itemId;
    private String author;
    private String title;
    private String description;
    private String link;
    private String image;
    private Date date;

    private boolean isReaded = false;

//==================================================================================================
//    Class Methods
//==================================================================================================

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getItemId(){
        return itemId;
    }

    public void setItemId(String itemId){
        this.itemId = itemId;
    }

    public long getFeedId(){
        return feedId;
    }

    public void setFeedId(long feedId){
        this.feedId = feedId;
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

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public boolean isReaded(){
        return isReaded;
    }

    public void setReaded(boolean readed){
        isReaded = readed;
    }

//==================================================================================================
//    Class Specific Methods
//==================================================================================================

    public void autoIncrementId(Realm realm){
        Number currentId = realm.where(this.getClass()).max("id");
        long autoIncrementId = (currentId == null ? 0 : currentId.longValue() + 1);
        setId(autoIncrementId);
    }

}
