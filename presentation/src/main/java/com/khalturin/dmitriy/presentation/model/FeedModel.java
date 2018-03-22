package com.khalturin.dmitriy.presentation.model;

import com.khalturin.dmitriy.presentation.transformer.DateToStringTransformer;

import java.util.Date;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 11.08.17 14:48.
 */

public class FeedModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private long mId;
  private String mUrl;
  private String mAuthor;
  private String mTitle;
  private String mDescription;
  private String mLink;
  private String mImage;
  private String mDate;
  private String mCopyright;

  private String mCreateDate;
  private String mUpdateDate;

//==================================================================================================
//    Class Methods
//==================================================================================================

  public long getId(){
    return mId;
  }

  public void setId(long mId){
    this.mId = mId;
  }

  public String getUrl(){
    return mUrl;
  }

  public void setUrl(String mUrl){
    this.mUrl = mUrl;
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

  public String getCopyright(){
    return mCopyright;
  }

  public void setCopyright(String mCopyright){
    this.mCopyright = mCopyright;
  }

  public String getCreateDate(){
    return mCreateDate;
  }

  public void setCreateDate(Date mCreateDate){
    this.mCreateDate = DateToStringTransformer.simple(mCreateDate);
  }

  public void setCreateDate(String mCreateDate){
    this.mCreateDate = mCreateDate;
  }

  public String getUpdateDate(){
    return mUpdateDate;
  }

  public void setUpdateDate(Date mUpdateDate){
    this.mUpdateDate = DateToStringTransformer.simple(mUpdateDate);
  }

  public void setUpdateDate(String mUpdateDate){
    this.mUpdateDate = mUpdateDate;
  }

}
