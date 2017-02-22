package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.vo.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:31.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private List<News> list = new ArrayList<>();

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsAdapter(){
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_card_news, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position){
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

}
