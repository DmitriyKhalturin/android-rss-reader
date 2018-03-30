package com.khalturin.dmitriy.presentation.view.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 01.08.17 17:27.
 */

// TODO: fix animation
public class RssUrlLayout extends LinearLayout implements FloatingLayout {

//==================================================================================================
//    Class Variables
//==================================================================================================

  private static final int sDuration = 300;

  private boolean mReady = true;
  private boolean mVisible = true;

//==================================================================================================
//    Class Constructor
//==================================================================================================

  public RssUrlLayout(Context context){
    super(context);
  }

  public RssUrlLayout(Context context, @Nullable AttributeSet attrs){
    super(context, attrs);
  }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

  @Override
  public void setVisibility(int visibility){
    super.setVisibility(visibility);

    mVisible = (visibility == VISIBLE);
  }

//==================================================================================================
//    Class Implementation FloatingLayout
//==================================================================================================

  public void setVisible(){
    if(mReady){
      mReady = false;

      setVisibility(VISIBLE);
      setAlpha(0f);

      int height = getHeight();

      setTop(-height);

      animate()
        .translationY(0)
        .alpha(1f)
        .setDuration(sDuration)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation){
            mVisible = true;
            mReady = true;
          }
        });
    }
  }

  public void setInvisible(){
    if(mReady){
      mReady = false;

      int height = getHeight();

      animate()
        .translationY(-height)
        .alpha(0)
        .setDuration(sDuration)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation){
            setVisibility(GONE);
            mVisible = false;
            mReady = true;
          }
        });
    }
  }

  public void changeVisibility(){
    if(mVisible){
      setInvisible();
    }else{
      setVisible();
    }
  }

}
