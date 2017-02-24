package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.vo.News;
import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:30.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private Long id = null;

    private PublishSubject<Long> onClickCard = PublishSubject.create();

    private LinearLayout cardView;
    private TextView titleView;
    private TextView dateView;
    private ImageView imageView;
    private TextView descriptionView;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsViewHolder(View view){
        super(view);

        cardView = (LinearLayout) view.findViewById(R.id.news_card);
        titleView = (TextView) view.findViewById(R.id.news_title);
        dateView = (TextView) view.findViewById(R.id.news_date);
        // imageView = (ImageView) view.findViewById(R.id.news_image);
        descriptionView = (TextView) view.findViewById(R.id.news_description);

        RxView.clicks(cardView).subscribe(aVoid -> {
            onClickCard.onNext(id);
        });
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public void bind(News news){
        id = news.getId();

        titleView.setText(news.getTitle());
        dateView.setText(news.getDate());
        imageView.setImageBitmap(news.getImage());
        descriptionView.setText(news.getDescription());

        if(news.isReaded()){
            cardView.setBackgroundResource(R.drawable.bg_news_readed);
            imageView.setBackgroundResource(R.drawable.bg_news_readed);
        }else{
            cardView.setBackgroundResource(R.drawable.bg_news_unread);
            imageView.setBackgroundResource(R.drawable.bg_news_unread);
        }
    }

    public Observable<Long> getOnClickCard(){
        return onClickCard.asObservable();
    }

}
