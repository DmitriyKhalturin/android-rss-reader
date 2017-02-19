package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

    @BindView(R.id.news_title)
    protected TextView title;

    @BindView(R.id.news_date)
    protected TextView date;

    @BindView(R.id.news_image)
    protected ImageView image;

    @BindView(R.id.news_text)
    protected TextView text;

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
        title.setText(news.getTitle());
        date.setText(news.getDate());
        image.setImageBitmap(news.getImage());
        text.setText(news.getText());
    }

}
