package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:30.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @BindView(R.id.news_layout)
    protected LinearLayout layoutView;

    @BindView(R.id.news_title)
    protected TextView titleView;

    @BindView(R.id.news_date)
    protected TextView dateView;

    @BindView(R.id.news_image)
    protected ImageView imageView;

    @BindView(R.id.news_description)
    protected TextView descriptionView;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsViewHolder(View view){
        super(view);

        ButterKnife.bind(view);
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public void bind(News news){
        titleView.setText(news.getTitle());
        dateView.setText(news.getDate());
        imageView.setImageBitmap(news.getImage());
        descriptionView.setText(news.getDescription());

        if(news.isReaded()){
            layoutView.setBackgroundResource(R.drawable.bg_news_readed);
            imageView.setBackgroundResource(R.drawable.bg_news_readed);
        }else{
            layoutView.setBackgroundResource(R.drawable.bg_news_unread);
            imageView.setBackgroundResource(R.drawable.bg_news_unread);
        }
    }

}
