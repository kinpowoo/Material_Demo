package com.szsszwl.materail_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bilibili.magicasakura.widgets.TintButton;
import com.bilibili.magicasakura.widgets.TintTextView;
import com.szsszwl.frameworkproj.common_page.BaseActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.util.DataBus;
import com.szsszwl.frameworkproj.util.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusDemoActivity extends BaseActivity {

    @BindView(R.id.post_message)
    TintButton postMessage;

    @BindView(R.id.content)
    TintTextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_demo_activity);
        ButterKnife.bind(this);
        setupToolbar();

        String data = (String) DataBus.get().pull("message2");
        content.setText(data);
    }

    @OnClick(R.id.post_message)
    public void onClick(View v){
        RxBus.get().post("message1","我是从MessageDemoActivity发出的消息");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
