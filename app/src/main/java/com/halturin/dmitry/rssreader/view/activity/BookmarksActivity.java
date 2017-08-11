package com.halturin.dmitry.rssreader.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.BookmarksPresenter;
import com.halturin.dmitry.rssreader.presenter.vo.Feed;
import com.halturin.dmitry.rssreader.view.BookmarksView;
import com.halturin.dmitry.rssreader.view.adapter.BookmarksAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 22:00.
 */

public class BookmarksActivity extends BaseActivity implements BookmarksView {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private BookmarksAdapter adapter = null;

    @BindView(R.id.toolbar)
    protected Toolbar toolbarView;

    @BindView(R.id.search_input)
    protected EditText searchInput;

    @BindView(R.id.bookmarks_list)
    protected RecyclerView bookmarkList;

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
    }

//==================================================================================================
//    Class Methods
//==================================================================================================



//==================================================================================================
//    Class Implementation BookmarksView
//==================================================================================================

    @Override
    public void setList(List<Feed> list){
        adapter.setList(list);
    }

}
