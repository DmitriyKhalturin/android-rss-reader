package com.halturin.dmitry.rssreader.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.FeedPresenter;
import com.halturin.dmitry.rssreader.presenter.vo.News;
import com.halturin.dmitry.rssreader.view.FeedView;
import com.halturin.dmitry.rssreader.view.adapter.NewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 20:28.
 */

public class FeedActivity extends RssActivity implements FeedView,
    SwipeRefreshLayout.OnRefreshListener {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.news_refresh)
    protected SwipeRefreshLayout refresh;
    @BindView(R.id.news_list)
    protected RecyclerView list;
    private NewsAdapter adapter = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedActivity(){
        rssPresenter = new FeedPresenter(this);
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setSwipeRefreshSettings();
        setRecyclerViewSettings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.action_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);

                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setSwipeRefreshSettings(){
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(R.color.colorPrimary);
    }

    private void setRecyclerViewSettings(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter();

        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

//==================================================================================================
//    Class Implementation FeedView
//==================================================================================================

    @Override
    public void setList(List<News> list){
        adapter.setList(list);
    }

    @Override
    public Observable<Void> getOnUpdateFeed(){
        return null;
    }

    @Override
    public void setUpdateComplete(){
        refresh.setRefreshing(false);
    }

//==================================================================================================
//    Class Implementation SwipeRefreshLayout.OnRefreshListener
//==================================================================================================

    @Override
    public void onRefresh(){
        // TODO: request news and update feed
    }

}
