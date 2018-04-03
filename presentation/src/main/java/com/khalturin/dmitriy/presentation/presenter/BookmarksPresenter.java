package com.khalturin.dmitriy.presentation.presenter;

import android.arch.lifecycle.ViewModel;

import com.khalturin.dmitriy.presentation.view.BookmarksView;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:01.
 */

public class BookmarksPresenter extends ViewModel {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private BookmarksView view = null;
//    private BookmarksMapper mapper = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public BookmarksPresenter(BookmarksView view){
        this.view = view;
//        this.mapper = new BookmarksMapper();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void onErrorBookmarksList(Throwable error){}

    private void setBookmarksList(){
//        addSubscription(rssModel.getFeedsList()
//            .map(mapper)
//            .subscribe(view::setList, this::onErrorBookmarksList));
    }

    private void onErrorSearchInFeed(Throwable error){}

    private void onSearchInFeed(CharSequence text){
        String searchText = text.toString();

        if(searchText.isEmpty()){
            setBookmarksList();
        }else{
//            addSubscription(rssModel.getFeedsListWithSearch(searchText)
//                .map(mapper)
//                .subscribe(view::setList, this::onErrorSearchInFeed));
        }
    }

    private void onErrorLoadFeed(Throwable error){}

    private void onLoadFeed(long feedId){
//        addSubscription(rssModel.setFeed(feedId)
//            .subscribe(this::onLoadFeedComplete, this::onErrorLoadFeed));
    }

    private void onErrorDeleteFeed(Throwable error){}

    private void onDeleteFeed(long feedId){
//        addSubscription(rssModel.removeFeed(feedId)
//            .subscribe(this::onDeleteFeedComplete, this::onErrorDeleteFeed));
    }

    private void setActionListeners(){
//        addSubscription(view.getOnSearchChange()
//            .subscribe(this::onSearchInFeed));
//        addSubscription(view.getOnLoadFeed()
//            .subscribe(this::onLoadFeed));
//        addSubscription(view.getOnDeleteFeed()
//            .subscribe(this::onDeleteFeed));
    }

    private void onLoadFeedComplete(Void aVoid){
//        view.setLoadFeedComplete();
    }

    private void onDeleteFeedComplete(Void aVoid){
//        view.setDeleteFeedComplete();
    }

}
