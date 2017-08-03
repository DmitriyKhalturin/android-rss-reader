package com.halturin.dmitry.rssreader.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.FeedPresenter;
import com.halturin.dmitry.rssreader.presenter.vo.News;
import com.halturin.dmitry.rssreader.view.FeedView;
import com.halturin.dmitry.rssreader.view.FloatingLayout;
import com.halturin.dmitry.rssreader.view.adapter.FeedAdapter;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.halturin.dmitry.rssreader.view.activity.NewsActivity.NEWS_ID;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 20:28.
 */

public class FeedActivity extends BaseActivity implements FeedView,
    SwipeRefreshLayout.OnRefreshListener {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private FeedAdapter adapter = null;

    private PublishSubject<Void> onUpdateList = PublishSubject.create();
    private PublishSubject<String> onUpdateUrl = PublishSubject.create();

    private FloatingLayout floatingLayout;

    private boolean isUpdateUrlReady = true;

    @BindView(R.id.toolbar)
    protected Toolbar toolbarView;

    @BindView(R.id.rss_url_layout)
    protected LinearLayout rssUrlLayout;

    @BindView(R.id.rss_url_icon)
    protected ImageView rssUrlIcon;

    @BindView(R.id.rss_url_loader)
    protected ProgressBar rssUrlLoader;

    @BindView(R.id.rss_url_input)
    protected EditText rssUrlInput;

    @BindView(R.id.rss_url_button)
    protected ImageButton rssUrlButton;

    @BindView(R.id.feed_refresh)
    protected SwipeRefreshLayout refreshView;

    @BindView(R.id.news_list)
    protected RecyclerView listView;

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

        setSupportActionBar(toolbarView);

        setSwipeRefreshSettings();
        setRecyclerViewSettings();
        setRssUrlLayoutSettings();
        setProgressBarVisible(false);
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
            case  R.id.action_add_news:
                floatingLayout.changeVisibility();
                break;
            case R.id.action_bookmarks:
                Intent intent = new Intent(this, BookmarksActivity.class);

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
        refreshView.setOnRefreshListener(this);
        refreshView.setColorSchemeResources(R.color.colorRefreshOne,
            R.color.colorRefreshTwo, R.color.colorRefreshThree);
    }

    private void setRecyclerViewSettings(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new FeedAdapter();

        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);

        adapter.getOnClickNews().filter(newsId -> newsId != null).subscribe(newsId -> {
            Intent intent = new Intent(this, NewsActivity.class);
            intent.putExtra(NEWS_ID, newsId);

            startActivity(intent);
        });
    }

    private void setRssUrlLayoutSettings(){
        floatingLayout = (FloatingLayout) rssUrlLayout;

        int delay = 1000;
        Handler handler = new Handler();

        rssUrlLayout.setVisibility(GONE);

        handler.postDelayed(() -> {
            List<News> list = adapter.getList();
            int size = list.size();

            if(size == 0){
                floatingLayout.setVisible();
            }
        }, delay);

        RxView.clicks(rssUrlButton).subscribe(aVoid -> {
            if(isUpdateUrlReady){
                isUpdateUrlReady = false;
                setProgressBarVisible(true);

                String url = rssUrlInput.getText().toString();

                onUpdateUrl.onNext(url);
            }
        });
    }

    private void setProgressBarVisible(boolean visible){
        if(visible){
            rssUrlLoader.setVisibility(VISIBLE);
            rssUrlIcon.setVisibility(GONE);
        }else{
            rssUrlLoader.setVisibility(GONE);
            rssUrlIcon.setVisibility(VISIBLE);
        }
    }

//==================================================================================================
//    Class Implementation FeedView
//==================================================================================================

    @Override
    public Observable<String> getOnUpdateUrl(){
        return onUpdateUrl.asObservable();
    }

    @Override
    public void setUpdateUrlComplete(){
        setProgressBarVisible(false);
        isUpdateUrlReady = true;
    }

    @Override
    public void setList(List<News> list){
        adapter.setList(list);
    }

    @Override
    public Observable<Void> getOnUpdateList(){
        return onUpdateList.asObservable();
    }

    @Override
    public void setUpdateListComplete(){
        refreshView.setRefreshing(false);
    }

//==================================================================================================
//    Class Implementation SwipeRefreshLayout.OnRefreshListener
//==================================================================================================

    @Override
    public void onRefresh(){
        onUpdateList.onNext(null);
    }

}
