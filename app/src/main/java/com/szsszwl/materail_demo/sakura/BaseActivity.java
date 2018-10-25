package com.szsszwl.materail_demo.sakura;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.szsszwl.materail_demo.R;


/**
 * Created by DeskTop29 on 2018/10/25.
 */

public class BaseActivity extends AppCompatActivity implements ActivityDelegate {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public Toolbar setupToolbar(){
       return setupToolbar(true);
    }

    public Toolbar setupToolbar(boolean backArrow){
        return setupToolbar(backArrow,null);
    }

    public Toolbar setupToolbar(BackCallBack callBack){
        return setupToolbar(true,callBack);
    }

    @Override
    public Toolbar setupToolbar(boolean backArrow, final BackCallBack callBack) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            if(backArrow) {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(callBack!=null){
                            callBack.beforeBack();
                        }
                        finish();
                    }
                });
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                toolbar.setNavigationIcon(R.drawable.back);
            }
        }
        return toolbar;
    }

    public interface BackCallBack{
        public void beforeBack();
    }
}
