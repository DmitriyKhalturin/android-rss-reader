package com.halturin.dmitry.rssreader.view.activity;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
    SwipeRefreshLayout.OnRefreshListener,
    MenuItemCompat.OnActionExpandListener,
    SearchView.OnQueryTextListener {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private Menu menu = null;

    private NewsAdapter adapter = null;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.news_refresher)
    protected SwipeRefreshLayout refresh;

    @BindView(R.id.news_list)
    protected RecyclerView list;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedActivity(){
        activityResId = R.layout.activity_feed;
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
        this.menu = menu;

        getMenuInflater().inflate(R.menu.action_bar_menu, menu);

        MenuItem search = menu.findItem(R.id.action_search);

        if(search != null){
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
            SearchView searchView = (SearchView) search.getActionView();

            if(searchView != null){
                searchView.setSearchableInfo(searchableInfo);
                searchView.setOnQueryTextListener(this);
                MenuItemCompat.setOnActionExpandListener(search, this);
            }
        }

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

    private void setVisibleMenuItems(@NonNull Menu menu, @NonNull MenuItem exclude, boolean visible){
        for(int i = 0; i < menu.size(); i++){
            MenuItem item = menu.getItem(i);

            if(item != exclude){
                item.setVisible(visible);
            }
        }
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

//==================================================================================================
//    Class Implementation MenuItemCompat.OnActionExpandListener
//==================================================================================================

    @Override
    public boolean onMenuItemActionExpand(MenuItem item){
        setVisibleMenuItems(menu, item, false);

        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item){
        setVisibleMenuItems(menu, item, true);

        return true;
    }

//==================================================================================================
//    Class Implementation SearchView.OnQueryTextListener
//==================================================================================================

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText){
        // TODO: update feed list

        return false;
    }

}
