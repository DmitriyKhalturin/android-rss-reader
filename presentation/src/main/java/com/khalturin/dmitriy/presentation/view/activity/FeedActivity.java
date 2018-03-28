package com.khalturin.dmitriy.presentation.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.khalturin.dmitriy.presentation.BR;
import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.binding.recycler.RecyclerConfigurator;
import com.khalturin.dmitriy.presentation.binding.recycler.adapter.BindingRecyclerAdapter;
import com.khalturin.dmitriy.presentation.databinding.ActivityFeedBinding;
import com.khalturin.dmitriy.presentation.presenter.FeedPresenter;
import com.khalturin.dmitriy.presentation.view.FeedView;
import com.khalturin.dmitriy.presentation.view.layout.FloatingLayout;
import com.khalturin.dmitriy.presentation.viewmodel.FeedViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.NewsViewModel;
import com.khalturin.dmitriy.presentation.viewmodel.RssUrlViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 20:28.
 */

public class FeedActivity extends BaseActivity implements FeedView,
  SwipeRefreshLayout.OnRefreshListener {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private FeedViewModel mFeedViewModel = new FeedViewModel();
  private RssUrlViewModel mRssUrlViewModel = new RssUrlViewModel();



  private FloatingLayout floatingLayout;

  private PublishSubject<Void> onUpdateList = PublishSubject.create();

//  private RssUrlViewModel rssUrlGetter = null;

  @BindView(R.id.toolbar)
  protected Toolbar toolbarView;

  @BindView(R.id.rss_url_layout)
  protected LinearLayout rssUrlLayout;

//  @BindView(R.id.rss_url_icon)
//  protected ImageView rssUrlIcon;
//
//  @BindView(R.id.rss_url_loader)
//  protected ProgressBar rssUrlLoader;
//
//  @BindView(R.id.rss_url_input)
//  protected EditText rssUrlInput;
//
//  @BindView(R.id.rss_url_button)
//  protected ImageButton rssUrlButton;

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
    ActivityFeedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);

    mFeedViewModel.recyclerConfigurator.set(getRecyclerConfigurator());

    binding.setFeedViewModel(mFeedViewModel);
    binding.setRssUrlViewModel(mRssUrlViewModel);



    ButterKnife.bind(this);

    setSupportActionBar(toolbarView);

    setSwipeRefreshSettings();
    setRssUrlLayoutSettings();
//    setRssUrlSetterSettings();
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

  @SuppressWarnings("unchecked")
  private RecyclerConfigurator getRecyclerConfigurator(){
    RecyclerConfigurator configurator = new RecyclerConfigurator();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    BindingRecyclerAdapter<NewsViewModel> adapter = new BindingRecyclerAdapter(R.layout.feed_card_view, null, BR.newsViewModel);

    configurator.setLayoutManager(layoutManager);
    configurator.setItemAnimator(itemAnimator);
    configurator.setAdapter(adapter);

    return configurator;
  }

  private void setSwipeRefreshSettings(){
    refreshView.setOnRefreshListener(this);
    refreshView.setColorSchemeResources(R.color.colorRefreshOne,
      R.color.colorRefreshTwo, R.color.colorRefreshThree);
  }

  private void setRssUrlLayoutSettings(){
    floatingLayout = (FloatingLayout) rssUrlLayout;

    int delay = 1000;
    Handler handler = new Handler();

    rssUrlLayout.setVisibility(GONE);

    handler.postDelayed(() -> {
      int size = mFeedViewModel.getItemCount();

      if(size == 0){
        floatingLayout.setVisible();
      }
    }, delay);
  }

//  private void setRssUrlSetterSettings(){
//    rssUrlGetter = new RssUrlViewModel(this, rssUrlButton, rssUrlInput, rssUrlLoader, rssUrlIcon);
//  }

//==================================================================================================
//    Class Implementation FeedView
//==================================================================================================

  @Override
  public Observable<String> getOnUpdateUrl(){
//    return rssUrlGetter.getOnUpdate();
    return PublishSubject.create();
  }

  @Override
  public void setUpdateUrlComplete(){
//    rssUrlGetter.setUpdateComplete();
  }

  @Override
  public void setItems(List<NewsViewModel> items){
    mFeedViewModel.setItems(items);
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
    floatingLayout.setInvisible();
    onUpdateList.onNext(null);
  }

}
