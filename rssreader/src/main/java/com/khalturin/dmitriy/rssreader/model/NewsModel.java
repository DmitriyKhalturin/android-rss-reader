package com.khalturin.dmitriy.rssreader.model;

import com.khalturin.dmitriy.rssreader.transformer.DateToStringTransformer;

import java.util.Date;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 13:40.
 */

public class NewsModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private long mId;
  private String mAuthor;
  private String mTitle;
  private String mDescription;
  private String mLink;
  private String mImage;
  private String mDate;

  private boolean mIsWasRead;
  private boolean mIsFavorite;

//==================================================================================================
//    Class Methods
//==================================================================================================

  public long getId(){
    return mId;
  }

  public void setId(long mId){
    this.mId = mId;
  }

  public String getAuthor(){
    return mAuthor;
  }

  public void setAuthor(String mAuthor){
    this.mAuthor = mAuthor;
  }

  public String getTitle(){
    return mTitle;
  }

  public void setTitle(String mTitle){
    this.mTitle = mTitle;
  }

  public String getDescription(){
    return mDescription;
  }

  public void setDescription(String mDescription){
    this.mDescription = mDescription;
  }

  public String getLink(){
    return mLink;
  }

  public void setLink(String mLink){
    this.mLink = mLink;
  }

  public String getImage(){
    return mImage;
  }

  public void setImage(String mImage){
    this.mImage = mImage;
  }

  public String getDate(){
    return mDate;
  }

  public void setDate(Date mDate){
    this.mDate = DateToStringTransformer.simple(mDate);
  }

  public void setDate(String mDate){
    this.mDate = mDate;
  }

  public boolean isWasRead(){
    return mIsWasRead;
  }

  public void setWasRead(boolean mIsWasRead){
    mIsWasRead = mIsWasRead;
  }

  public boolean isFavorite(){
    return mIsFavorite;
  }

  public void setFavorite(boolean favorite){
    mIsFavorite = favorite;
  }

}
