package dmitriy.khalturin.com.view.recycler.binding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-binding-recycler-view on 14.04.18 19:13.
 */

public class BindingViewHolder extends RecyclerView.ViewHolder {

  private ViewDataBinding mBinding;

  public BindingViewHolder(View view){
    super(view);

    mBinding = DataBindingUtil.bind(view);
  }

  public ViewDataBinding getBinding(){
    return mBinding;
  }

}
