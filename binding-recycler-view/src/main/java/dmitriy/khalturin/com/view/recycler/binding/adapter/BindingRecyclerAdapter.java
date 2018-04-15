package dmitriy.khalturin.com.view.recycler.binding.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-binding-recycler-view on 14.04.18 19:13.
 */

public class BindingRecyclerAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {

  private int mLayoutId;
  private List<T> mItems;
  private int mVariableId;

  public BindingRecyclerAdapter(@LayoutRes int mLayoutId, @Nullable List<T> mItems, int mVariableId){
    this.mLayoutId = mLayoutId;
    this.mItems = (mItems != null ? mItems : new ArrayList<>());
    this.mVariableId = mVariableId;
  }

  @NonNull
  @Override
  public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext())
      .inflate(mLayoutId, parent, false);

    return new BindingViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BindingViewHolder holder, int position){
    T value = mItems.get(position);

    holder.getBinding().setVariable(mVariableId, value);
  }

  @Override
  public int getItemCount(){
    return mItems.size();
  }

  public List<T> getItems(){
    return mItems;
  }

  public void setItems(@Nullable List<T> mItems){
    if(mItems == null){
      this.mItems.clear();
    }else{
      this.mItems = mItems;
      notifyDataSetChanged();
    }
  }

}
