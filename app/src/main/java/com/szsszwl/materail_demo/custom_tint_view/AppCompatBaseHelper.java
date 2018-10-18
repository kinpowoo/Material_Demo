package com.szsszwl.materail_demo.custom_tint_view;

import android.util.AttributeSet;
import android.view.View;

import com.bilibili.magicasakura.utils.TintManager;

/**
 * Created by DeskTop29 on 2018/10/18.
 */

public abstract class AppCompatBaseHelper<T extends View> {
    protected T mView;
    protected TintManager mTintManager;
    private boolean mSkipNextApply;

    public AppCompatBaseHelper(T view, TintManager tintManager) {
        this.mView = view;
        this.mTintManager = tintManager;
    }

    protected boolean skipNextApply() {
        if(this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return true;
        } else {
            this.mSkipNextApply = true;
            return false;
        }
    }

    protected void setSkipNextApply(boolean flag) {
        this.mSkipNextApply = flag;
    }

    abstract void loadFromAttribute(AttributeSet var1, int var2);

    public abstract void tint();
}
