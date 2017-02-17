package com.halturin.dmitry.rssreader.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.halturin.dmitry.rssreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 22:00.
 */

public class SettingsActivity extends AppCompatActivity {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public SettingsActivity(){
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
