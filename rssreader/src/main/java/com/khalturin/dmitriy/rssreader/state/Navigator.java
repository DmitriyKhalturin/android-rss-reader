package com.khalturin.dmitriy.rssreader.state;

import android.content.Context;
import android.content.Intent;

import com.khalturin.dmitriy.rssreader.view.activity.BookmarksActivity;
import com.khalturin.dmitriy.rssreader.view.activity.NewsActivity;

import static com.khalturin.dmitriy.rssreader.view.activity.NewsActivity.NEWS_ID;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 21.03.18 18:10.
 */

public class Navigator {

  private Context context;

  public Navigator(Context context){
    this.context = context;
  }

  public void navigateToNews(Long newsId){
    Intent intent = new Intent(context, NewsActivity.class);

    intent.putExtra(NEWS_ID, newsId);
    context.startActivity(intent);
  }

  public void navigateToBookmarks(){
    Intent intent = new Intent(context, BookmarksActivity.class);

    context.startActivity(intent);
  }

}
