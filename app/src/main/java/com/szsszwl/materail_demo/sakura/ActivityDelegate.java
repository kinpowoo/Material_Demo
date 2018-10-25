package com.szsszwl.materail_demo.sakura;


import android.support.v7.widget.Toolbar;


/**
 * Created by DeskTop29 on 2018/10/25.
 */

public interface ActivityDelegate {
    public abstract Toolbar setupToolbar(boolean backArrow, BaseActivity.BackCallBack callBack);
}
