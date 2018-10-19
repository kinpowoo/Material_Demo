package com.szsszwl.materail_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.widgets.TintToolbar;
import com.szsszwl.materail_demo.sakura.ThemeHelper;

import butterknife.BindView;

/**
 * Created by DeskTop29 on 2018/10/19.
 */

public class CollapsingLayoutActivity extends AppCompatActivity {

    TintToolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_layout_activity);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctb);

        //设置导航键为返回键
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.back);


        //跟据主题来设置 contentScrim 颜色
        collapsingToolbarLayout.setContentScrimColor(ThemeHelper.getCurrentPrimaryColor(this));


    }


    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private void setTitleToCollapsingToolbarLayout() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -toolbar.getHeight() / 2) {
                    toolbar.setAlpha(0f);
                    //collapsingToolbarLayout.setTitle("滚动导航栏");
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    //collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    toolbar.setAlpha(1f);
                    //collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }

}
