package com.halturin.dmitry.rssreader.presenter.vo;

import java.util.Date;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 11.08.17 14:48.
 */

public class Feed {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private long id;
    private String url;
    private String author;
    private String title;
    private String description;
    private String link;
    private String image;
    private String date;
    private String copyright;

    private boolean isActive;

    private boolean isFavorite;

    private String createDate;
    private String updateDate;

//==================================================================================================
//    Class Methods
//==================================================================================================

    public long getId(){
        return id;
    }

    public void setId(long id){
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

    public String getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date.toString();
    }

    public void setDate(String date){
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

    public String getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate = createDate.toString();
    }

    public void setCreateDate(String createDate){
        this.createDate = createDate;
    }

    public String getUpdateDate(){
        return updateDate;
    }

    public void setUpdateDate(Date updateDate){
        this.updateDate = updateDate.toString();
    }

    public void setUpdateDate(String updateDate){
        this.updateDate = updateDate;
    }

}