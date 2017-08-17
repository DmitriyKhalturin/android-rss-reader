package com.halturin.dmitry.rssreader.model.dto;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 22.02.17 1:26.
 */

public class FeedEntity extends RealmObject {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @PrimaryKey
    private long id;

    private String url;

    private String author;
    private String title;
    private String description;
    private String link;
    private String image;
    private Date date;
    private String copyright;

    private boolean isActive = false;

    private boolean isFavorite = false;

    private Date createDate;
    private Date updateDate;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedEntity(){
        Date date = getCurrentDate();

        setCreateDate(date);
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public long getId(){
        return id;
    }

    private void setId(long id){
        this.id = id;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
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

    public String getCopyright(){
        return copyright;
    }

    public void setCopyright(String copyright){
        this.copyright = copyright;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean active){
        isActive = active;
    }

    public boolean isFavorite(){
        return isFavorite;
    }

    public void setFavorite(boolean favorite){
        isFavorite = favorite;
    }

    public Date getCreateDate(){
        return createDate;
    }

    private void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getUpdateDate(){
        return updateDate;
    }

    private void setUpdateDate(Date updateDate){
        this.updateDate = updateDate;
    }

//==================================================================================================
//    Class Specific Methods
//==================================================================================================

    public void autoIncrementId(Realm realm){
        Number currentId = realm.where(this.getClass()).max("id");
        long autoIncrementId = (currentId == null ? 0 : currentId.longValue() + 1);
        setId(autoIncrementId);
    }

    public void setCurrentUpdateDate(){
        Date date = getCurrentDate();

        setUpdateDate(date);
    }

    private Date getCurrentDate(){
        Date date = new Date();
        long time = System.currentTimeMillis();

        date.setTime(time);

        return date;
    }

}
