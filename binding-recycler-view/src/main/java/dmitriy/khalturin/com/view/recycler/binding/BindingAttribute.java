package dmitriy.khalturin.com.view.recycler.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-binding-recycler-view on 14.04.18 19:13.
 */

public final class BindingAttribute {

  @BindingAdapter("bind:recyclerManager")
  public static void bindRecyclerManager(RecyclerView recyclerView, RecyclerManager recyclerManager){
    recyclerView.setLayoutManager(recyclerManager.getLayoutManager());
    recyclerView.setItemAnimator(recyclerManager.getItemAnimator());
    recyclerView.setAdapter(recyclerManager.getAdapter());
  }

}
