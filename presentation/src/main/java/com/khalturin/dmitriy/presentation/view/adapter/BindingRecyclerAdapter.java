package com.khalturin.dmitriy.presentation.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 21:58.
 */

public class BindingRecyclerAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {

  private int layoutId;
  private List<T> list;
  private int variableId;

  public BindingRecyclerAdapter(@LayoutRes int layoutId, List<T> list, int variableId){
    this.layoutId = layoutId;
    this.list = list;
    this.variableId = variableId;
  }

  @NonNull
  @Override
  public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext())
      .inflate(layoutId, parent, false);

    return new BindingViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BindingViewHolder holder, int position){
    T value = list.get(position);

    holder.getBinding().setVariable(variableId, value);
  }

  @Override
  public int getItemCount(){
    return list.size();
  }

  public List<T> getList(){
    return list;
  }

  public void setList(List<T> collection){
    if(list != null){
      list.clear();
      list.addAll(collection);

      notifyDataSetChanged();
    }
  }

}
