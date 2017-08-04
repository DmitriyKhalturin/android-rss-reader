package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.vo.News;
import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:30.
 */

public class FeedCardViewHolder extends RecyclerView.ViewHolder {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private Long id = null;

    private PublishSubject<Long> onClickNews = PublishSubject.create();

    private RelativeLayout cardView;
    private TextView titleView;
    private TextView dateView;
    private ImageView imageView;
    private TextView descriptionView;
    private View fogView;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedCardViewHolder(View view){
        super(view);

        cardView = (RelativeLayout) view.findViewById(R.id.news_card);
        titleView = (TextView) view.findViewById(R.id.news_title);
        dateView = (TextView) view.findViewById(R.id.news_date);
        imageView = (ImageView) view.findViewById(R.id.news_image);
        descriptionView = (TextView) view.findViewById(R.id.news_description);
        fogView = view.findViewById(R.id.news_fog);

        RxView.clicks(cardView).subscribe(aVoid -> {
            onClickNews.onNext(id);
        });
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public void bind(News news){
        id = news.getId();

        titleView.setText(news.getTitle());
        dateView.setText(news.getDate());
        // imageView.setImageBitmap(news.getImage());
        descriptionView.setText(news.getDescription());

        boolean isReaded = news.isReaded();

        if(isReaded){
            fogView.setVisibility(GONE);
        }else{
            fogView.setVisibility(VISIBLE);
        }
    }

    public Observable<Long> getOnClickNews(){
        return onClickNews.asObservable();
    }

}
