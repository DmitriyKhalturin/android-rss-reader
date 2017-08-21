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
            .subscribe(view::setList));
    }

    private void setActionListeners(){
        addSubscription(view.getOnSearchChange()
            .subscribe(searchText -> {
                // TODO: add implementation to model
            }));
        addSubscription(view.getOnLoadFeed()
            .subscribe(feedId -> {
                // TODO: add implementation to model
            }));
        addSubscription(view.getOnDeleteFeed()
            .subscribe(feedId -> {
                rssModel.removeFeed(feedId)
                    .subscribe(this::onDeleteFeedComplete);
            }));
    }

    private void onLoadFeedComplete(){
        view.setLoadFeedComplete();
    }

    private void onDeleteFeedComplete(Void aVoid){
        view.setDeleteFeedComplete();
    }

}
