package com.szsszwl.materail_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.szsszwl.frameworkproj.common_page.BaseActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.webview.base.BaseWebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WebDemoActivity extends BaseActivity {

    @BindView(R.id.action_activity)
    Button actionActivity;
    @BindView(R.id.action_fragment)
    Button actionFragment;
    @BindView(R.id.action_download)
    Button actionDownload;
    @BindView(R.id.action_upload_by_input)
    Button actionUploadByInput;
    @BindView(R.id.action_upload_by_js)
    Button actionUploadByJs;
    @BindView(R.id.action_call)
    Button actionCall;
    @BindView(R.id.action_full_screen)
    Button actionFullScreen;
    @BindView(R.id.action_custom_indicator)
    Button actionCustomIndicator;
    @BindView(R.id.action_auto_hide_tool_bar)
    Button actionAutoHideToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_demo_activity);
        ButterKnife.bind(this);
        setupToolbar();

    }


    @OnClick({R.id.action_activity,R.id.action_fragment,R.id.action_download,
        R.id.action_upload_by_input,R.id.action_upload_by_js,R.id.action_call,
        R.id.action_full_screen,R.id.action_custom_indicator,R.id.action_auto_hide_tool_bar})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.action_activity:
                startActivity(new Intent(this, BaseWebActivity.class));
                break;
            case R.id.action_fragment:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY,CommonActivity.USE_IN_FRAGMENT));
                break;
            case R.id.action_download:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY, CommonActivity.FILE_DOWNLOAD));
                break;
            case R.id.action_upload_by_input:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY, CommonActivity.INPUT_TAG_PROBLEM));
                break;
            case R.id.action_upload_by_js:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY,CommonActivity.JS_JAVA_COMUNICATION_UPLOAD_FILE));
                break;
            case R.id.action_call:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY,CommonActivity.JS_JAVA_COMMUNICATION));
                break;
            case R.id.action_full_screen:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY,CommonActivity.VIDEO_FULL_SCREEN));
                break;
            case R.id.action_custom_indicator:
                startActivity(new Intent(this, CommonActivity.class)
                        .putExtra(CommonActivity.TYPE_KEY,CommonActivity.CUSTOM_PROGRESSBAR));
                break;
            case R.id.action_auto_hide_tool_bar:
                break;
        }
    }
}
