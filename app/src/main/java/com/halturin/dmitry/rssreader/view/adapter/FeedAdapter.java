package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.app.util.SubscribersList;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:31.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedCardViewHolder> {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private List<News> list = new ArrayList<>();

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
        News news = list.get(position);

        holder.bind(news);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public List<News> getList(){
        return list;
    }

    public void setList(List<News> list){
        if(list != null){
            this.list = list;

            notifyDataSetChanged();
        }
    }

    public Observable<Long> getOnClickNews(){
        return onClickNews.getObservable();
    }

}
