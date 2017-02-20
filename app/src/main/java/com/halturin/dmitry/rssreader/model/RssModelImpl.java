package com.halturin.dmitry.rssreader.model;

import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.util.List;

import rx.Observable;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:03.
 */

public class RssModelImpl implements RssModel {

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public RssModelImpl(){
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    @Override
    public String getUrl(){
        return null;
    }

    @Override
    public void setUrl(String url){

    }

    @Override
    public Observable<Boolean> updateFeed(){
        return null;
    }

    @Override
    public Observable<List<News>> getFeed(){
        return null;
    }

    @Override
    public News getNews(long id){
        return null;
    }

}
