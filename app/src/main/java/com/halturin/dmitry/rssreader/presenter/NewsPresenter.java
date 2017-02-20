package com.halturin.dmitry.rssreader.presenter;

import com.halturin.dmitry.rssreader.view.NewsView;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:01.
 */

public class NewsPresenter extends RssPresenterImpl {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private NewsView view = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsPresenter(NewsView view){
        this.view = view;
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void onResume(){
        super.onResume();

        long newsId = view.getNewsId();

        view.setContent(rssModel.getNews(newsId));
    }

}
