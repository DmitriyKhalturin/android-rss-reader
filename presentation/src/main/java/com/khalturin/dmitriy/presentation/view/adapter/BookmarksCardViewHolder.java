package com.khalturin.dmitriy.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.model.FeedModel;
import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 19.02.17 14:30.
 */

public class BookmarksCardViewHolder extends RecyclerView.ViewHolder {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private Long id = null;

    private PublishSubject<Long> onLoadFeed = PublishSubject.create();
    private PublishSubject<Long> onDeleteFeed = PublishSubject.create();

    private Context context;

    private LinearLayout cardView;
    private RelativeLayout collapsedView;
    private TextView urlView;
    private TextView titleView;
    private TextView authorView;
    private TextView lastUpdateView;
    private ImageView imageView;
    private TextView descriptionView;
    private ImageButton loadButton;
    private ImageButton deleteButton;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public BookmarksCardViewHolder(View view){
        super(view);

        context = view.getContext();

        cardView = (LinearLayout) view.findViewById(R.id.bookmark_card);
        collapsedView = (RelativeLayout) view.findViewById(R.id.bookmark_collapsed);
        urlView = (TextView) view.findViewById(R.id.bookmark_url);
        titleView = (TextView) view.findViewById(R.id.bookmark_title);
        authorView = (TextView) view.findViewById(R.id.bookmark_author);
        lastUpdateView = (TextView) view.findViewById(R.id.bookmark_last_update);
        imageView = (ImageView) view.findViewById(R.id.bookmark_image);
        descriptionView = (TextView) view.findViewById(R.id.bookmark_description);
        loadButton = (ImageButton) view.findViewById(R.id.bookmark_load_button);
        deleteButton = (ImageButton) view.findViewById(R.id.bookmark_delete_button);

        RxView.clicks(cardView).subscribe(this::changeCollapsedState);
        RxView.clicks(loadButton).subscribe(aVoid -> {
            onLoadFeed.onNext(id);
        });
        RxView.clicks(deleteButton).subscribe(aVoid -> {
            onDeleteFeed.onNext(id);
        });
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    public void bind(FeedModel feed){
        id = feed.getId();

//        Picasso.with(context).cancelRequest(imageView);

        urlView.setText(feed.getUrl());
        titleView.setText(feed.getTitle());
        authorView.setText(feed.getAuthor());
        lastUpdateView.setText(feed.getUpdateDate());

//        Picasso.with(context)
//            .load(feed.getImage())
//            .into(imageView);

        descriptionView.setText(feed.getDescription());
    }

    public void changeCollapsedState(Void aVoid){
        // TODO: implementation animation collapse
    }

    public Observable<Long> getOnLoadFeed(){
        return onLoadFeed.asObservable();
    }

    public Observable<Long> getOnDeleteFeed(){
        return onDeleteFeed.asObservable();
    }

}