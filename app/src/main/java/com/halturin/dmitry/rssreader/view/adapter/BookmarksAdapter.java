package com.halturin.dmitry.rssreader.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.vo.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:31.
 */

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksCardViewHolder> {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private List<Feed> list = new ArrayList<>();

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public BookmarksAdapter(){
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public BookmarksCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.feed_card_view, parent, false);
        BookmarksCardViewHolder holder = new BookmarksCardViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BookmarksCardViewHolder holder, int position){
        Feed feed = list.get(position);

        holder.bind(feed);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public List<Feed> getList(){
        return list;
    }

    public void setList(List<Feed> list){
        if(list != null){
            this.list = list;

            notifyDataSetChanged();
        }
    }

}
