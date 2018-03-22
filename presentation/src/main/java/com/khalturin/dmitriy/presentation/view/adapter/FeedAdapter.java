package com.khalturin.dmitriy.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.util.SubscribersList;
import com.khalturin.dmitriy.presentation.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:31.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedCardViewHolder> {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private List<NewsModel> list = new ArrayList<>();

    private SubscribersList<Long> onClickNews = new SubscribersList<>();

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public FeedAdapter(){
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public FeedCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.feed_card_view, parent, false);
        FeedCardViewHolder holder = new FeedCardViewHolder(view);

        holder.getOnClickNews().subscribe(onClickNews::onNext);

        return holder;
    }

    @Override
    public void onBindViewHolder(FeedCardViewHolder holder, int position){
        NewsModel news = list.get(position);

        holder.bind(news);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public List<NewsModel> getList(){
        return list;
    }

    public void setList(List<NewsModel> list){
        if(list != null){
            this.list = list;

            notifyDataSetChanged();
        }
    }

    public Observable<Long> getOnClickNews(){
        return onClickNews.getObservable();
    }

}
