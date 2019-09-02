package dmitriykhalturin.rssreader.view.binding

import android.databinding.BindingAdapter
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 12.11.18 1:15.
 */
object ImageAttribute {

  @JvmStatic
  @BindingAdapter("imageUrl")
  fun bindImageUrl(imageView: ImageView, url: String?) {
    if(url is String){
      Glide.with(imageView).load(url).into(imageView)
      imageView.visibility = VISIBLE
    }else{
      imageView.visibility = GONE
    }
  }

}
