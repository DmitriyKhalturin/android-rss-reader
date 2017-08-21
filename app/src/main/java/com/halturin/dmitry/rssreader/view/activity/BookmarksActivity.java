package com.halturin.dmitry.rssreader.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.app.util.SubscribersList;
import com.halturin.dmitry.rssreader.presenter.BookmarksPresenter;
import com.halturin.dmitry.rssreader.presenter.vo.Feed;
import com.halturin.dmitry.rssreader.view.BookmarksView;
import com.halturin.dmitry.rssreader.view.adapter.BookmarksAdapter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 22:00.
 */

public class BookmarksActivity extends BaseActivity implements BookmarksView {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private BookmarksAdapter adapter = null;

    private SubscribersList<CharSequence> onSearchChange = new SubscribersList<>();
    private SubscribersList<Long> onLoadFeed = new SubscribersList<>();
    private SubscribersList<Long> onDeleteFeed = new SubscribersList<>();

    @BindView(R.id.toolbar)
    protected Toolbar toolbarView;

    @BindView(R.id.search_input)
    protected EditText searchInput;

    @BindView(R.id.bookmarks_list)
    protected RecyclerView listView;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public BookmarksActivity(){
        rssPresenter = new BookmarksPresenter(this);
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarView);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setRecyclerViewSettings();
        setSearchViewSettings();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setRecyclerViewSettings(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new BookmarksAdapter();

        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);

        adapter.getOnLoadFeed().filter(feedId -> feedId != null).subscribe(feedId -> {
            onLoadFeed.onNext(feedId);
        });
        adapter.getOnDeleteFeed().filter(feedId -> feedId != null).subscribe(feedId -> {
            onDeleteFeed.onNext(feedId);
        });
    }

    private void setSearchViewSettings(){
        RxTextView.textChanges(searchInput)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(onSearchChange::onNext);
    }

//==================================================================================================
//    Class Implementation BookmarksView
//==================================================================================================

    @Override
    public void setList(List<Feed> list){
        adapter.setList(list);
    }

    public Observable<CharSequence> getOnSearchChange(){
        return onSearchChange.getObservable();
    }

    @Override
    public Observable<Long> getOnLoadFeed(){
        return onLoadFeed.getObservable();
    }

    @Override
    public void setLoadFeedComplete(){
        // TODO: implementation later. stop loader. finish this activity. show feed activity
    }

    @Override
    public Observable<Long> getOnDeleteFeed(){
        return onDeleteFeed.getObservable();
    }

    @Override
    public void setDeleteFeedComplete(){
        // TODO: implementation animation deleting from list
    }

}
