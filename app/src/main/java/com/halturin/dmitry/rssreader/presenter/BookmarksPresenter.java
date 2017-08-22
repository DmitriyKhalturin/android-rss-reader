package com.halturin.dmitry.rssreader.presenter;

import com.halturin.dmitry.rssreader.presenter.mapper.BookmarksMapper;
import com.halturin.dmitry.rssreader.view.BookmarksView;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:01.
 */

public class BookmarksPresenter extends RssPresenterImpl {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private BookmarksView view = null;
    private BookmarksMapper mapper = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public BookmarksPresenter(BookmarksView view){
        this.view = view;
        this.mapper = new BookmarksMapper();
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void onResume(){
        super.onResume();

        setBookmarksList();
        setActionListeners();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setBookmarksList(){
        addSubscription(rssModel.getFeedsList()
            .map(mapper)
            .subscribe(view::setList, throwable -> {
                // TODO: processing exception
            }));
    }

    private void setActionListeners(){
        addSubscription(view.getOnSearchChange()
            .subscribe(searchText -> {
                rssModel.getFeedsListWithSearch(searchText.toString())
                    .map(mapper)
                    .subscribe(view::setList, throwable -> {
                        // TODO: processing exception
                    });
            }));
        addSubscription(view.getOnLoadFeed()
            .subscribe(feedId -> {
                rssModel.setFeed(feedId)
                    .subscribe(this::onLoadFeedComplete, throwable -> {
                        // TODO: processing exception
                    });
            }));
        addSubscription(view.getOnDeleteFeed()
            .subscribe(feedId -> {
                rssModel.removeFeed(feedId)
                    .subscribe(this::onDeleteFeedComplete, throwable -> {
                        // TODO: processing exception
                    });
            }));
    }

    private void onLoadFeedComplete(Void aVoid){
        view.setLoadFeedComplete();
    }

    private void onDeleteFeedComplete(Void aVoid){
        view.setDeleteFeedComplete();
    }

}
