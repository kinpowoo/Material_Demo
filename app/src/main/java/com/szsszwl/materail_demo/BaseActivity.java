package com.szsszwl.materail_demo;

import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.szsszwl.frameworkproj.common_page.BaseActivityFrameworkLibrary;

public class BaseActivity extends BaseActivityFrameworkLibrary {



    @Override
    public Toolbar setupToolbar(){
        return setupToolbar(true);
    }
    @Override
    public Toolbar setupToolbar(boolean backArrow){
        return setupToolbar(backArrow,null);
    }
    @Override
    public Toolbar setupToolbar(BackCallBack callBack){
        return setupToolbar(true,callBack);
    }
    @Override
    public Toolbar setupToolbar(boolean backArrow, final BackCallBack callBack) {
        Toolbar toolbar = findViewById(com.szsszwl.frameworkproj.R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            if(backArrow) {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(callBack!=null){
                            callBack.beforeBack();
                        }
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            finishAfterTransition();
                        }else{
                            finish();
                        }
                    }
                });
                //设置title是否可见
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                //设置返回箭头
                toolbar.setNavigationIcon(com.szsszwl.frameworkproj.R.drawable.framework_library_back);
            }else{
                //设置title是否可见
                getSupportActionBar().setDisplayShowTitleEnabled(true);
            }
        }
        return toolbar;
    }

}
