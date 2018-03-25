package com.khalturin.dmitriy.presentation.presenter;

import com.khalturin.dmitriy.presentation.view.NewsView;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:01.
 */

public class NewsPresenter extends RssPresenterImpl {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private NewsView view = null;
//    private NewsMapper mapper = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsPresenter(NewsView view){
        this.view = view;
//        this.mapper = new NewsMapper();
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void onResume(){
        super.onResume();

        setNews();
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    private void setNews(){
        long newsId = view.getNewsId();

//        addSubscription(rssModel.getItem(newsId)
//            .map(mapper)
//            .subscribe(view::setContent,
//                throwable -> {
//                    // TODO: processing exception
//                }));
    }

}
