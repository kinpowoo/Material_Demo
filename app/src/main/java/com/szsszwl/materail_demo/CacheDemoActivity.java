package com.szsszwl.materail_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.bilibili.magicasakura.widgets.TintButton;
import com.bilibili.magicasakura.widgets.TintTextView;
import com.szsszwl.frameworkproj.cache.DiskCacheEntity;
import com.szsszwl.frameworkproj.cache.LruDiskCache;
import com.szsszwl.frameworkproj.common_page.BaseActivityFrameworkLibrary;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CacheDemoActivity extends BaseActivity {

    @BindView(R.id.save_cache)
    TintButton saveCache;
    @BindView(R.id.read_cache)
    TintButton readCache;
    @BindView(R.id.remove_cache)
    TintButton removeCache;
    @BindView(R.id.text_area)
    TintTextView textArea;

    LruDiskCache lruDiskCache;     //缓存管理对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cache_demo_activity);
        ButterKnife.bind(this);
        setupToolbar();
        setupWindowAnimations();

        lruDiskCache = LruDiskCache.getDiskCache("file_cache");  //缓存标识

    }

    @OnClick({R.id.save_cache, R.id.read_cache, R.id.remove_cache})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_cache:
                DiskCacheEntity entity = new DiskCacheEntity.Builder()
                        .setKey("testCache")
                        .setString("我是测试存放的内容")
                        .setInt(23)
                        .setDate(new Date())
                        .setBool(true)
                        .setExpires(1000*30)
                        .build();

                lruDiskCache.put(entity);
                textArea.setText("保存成功");
                break;
            case R.id.read_cache:
                DiskCacheEntity cacheEntity = lruDiskCache.get("testCache");
                if (cacheEntity != null) {
                    String objContent = cacheEntity.toString();
                    textArea.setText(objContent);
                } else {
                    textArea.setText("没有找到缓存对象");
                }
                break;
            case R.id.remove_cache:
                lruDiskCache.clearCacheFiles();
                textArea.setText("");
                break;
        }
    }
}
