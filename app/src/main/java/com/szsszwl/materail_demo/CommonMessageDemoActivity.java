package com.szsszwl.materail_demo;

import android.widget.TextView;

import com.szsszwl.frameworkproj.common_page.CommonMessageActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.custom_view.SwitchTabView;

public class CommonMessageDemoActivity extends CommonMessageActivityFrameworkLibrary {


    @Override
    public String setTitle() {
        return "通用消息中心示例";
    }

    @Override
    public String[] setTabs() {
        return new String[]{"已读消息","未读消息","新消息"};
    }

    @Override
    public void getTab(SwitchTabView tab) {

    }

    @Override
    public void tabSwitched(TextView v, int index) {

    }

    @Override
    public void initViewAndLoadData() {

    }


}
