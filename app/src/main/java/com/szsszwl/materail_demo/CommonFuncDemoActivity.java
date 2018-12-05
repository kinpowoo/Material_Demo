package com.szsszwl.materail_demo;

import android.view.View;

import com.szsszwl.frameworkproj.common_page.CommonFuncActivityFrameworkLibrary;

public class CommonFuncDemoActivity extends CommonFuncActivityFrameworkLibrary {
    @Override
    public int inflateView() {
        return R.layout.content_main;
    }

    @Override
    public void initViews() {

    }

    @Override
    public String setTitle() {
        return "通用功能页";
    }

    @Override
    public String setItemTextColor() {
        return null;
    }

    @Override
    public float setItemTextSize() {
        return 0;
    }

    @Override
    public void itemClick(View v, int position) {

    }

    @Override
    public String[] setNames() {
        return new String[]{"更改用户信息","搜索","回到首页"};
    }

    @Override
    public int[] setIcons() {
        return new int[]{R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round};
    }
}
