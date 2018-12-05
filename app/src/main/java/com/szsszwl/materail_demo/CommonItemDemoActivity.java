package com.szsszwl.materail_demo;

import com.szsszwl.frameworkproj.common_page.CommonChildActivityFrameworkLibrary;

public class CommonItemDemoActivity extends CommonChildActivityFrameworkLibrary {

    @Override
    public int inflateView() {
        return R.layout.content_main;
    }

    @Override
    public void initViews() {
    }

    @Override
    public String setTitle() {
        return "通用子页面";
    }

    @Override
    public int inflateMenu() {
        return 0;
    }

    @Override
    public void menuClick(int menuId) {

    }
}
