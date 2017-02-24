package com.halturin.dmitry.rssreader.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.halturin.dmitry.rssreader.R;
import com.halturin.dmitry.rssreader.presenter.SettingsPresenter;
import com.halturin.dmitry.rssreader.view.SettingsView;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 17.02.17 22:00.
 */

public class SettingsActivity extends RssActivity implements SettingsView {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private PublishSubject<String> onChangeUrl = PublishSubject.create();

    @BindView(R.id.toolbar)
    protected Toolbar toolbarView;

    @BindView(R.id.setting_url)
    protected TextView urlView;

    @BindView(R.id.settings_sync)
    protected Button syncView;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public SettingsActivity(){
        rssPresenter = new SettingsPresenter(this);
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarView);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RxView.clicks(syncView)
            .subscribe(this::onSyncClick);
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void onSyncClick(Void aVoid){
        String url = urlView.getText().toString();

        urlView.setEnabled(false);
        syncView.setEnabled(false);

        onChangeUrl.onNext(url);
    }

//==================================================================================================
//    Class Implementation SettingsView
//==================================================================================================

    @Override
    public void setUrl(String url){
        urlView.setText(url);
    }

    @Override
    public Observable<String> getOnChangeUrl(){
        return onChangeUrl.asObservable();
    }

    @Override
    public void setChangeUrlComplete(){
        urlView.setEnabled(true);
        syncView.setEnabled(true);
    }

}
