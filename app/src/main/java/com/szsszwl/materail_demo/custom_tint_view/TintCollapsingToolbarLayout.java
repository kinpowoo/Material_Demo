package com.szsszwl.materail_demo.custom_tint_view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;

import com.bilibili.magicasakura.utils.TintManager;
import com.bilibili.magicasakura.widgets.*;

/**
 * Created by DeskTop29 on 2018/10/19.
 */

public class TintCollapsingToolbarLayout extends CollapsingToolbarLayout
        implements Tintable, AppCompatBackgroundHelper.BackgroundExtensible {
    private AppCompatBackgroundHelper mBackgroundHelper;

    public TintCollapsingToolbarLayout(Context context) {
        this(context, (AttributeSet)null);
    }

    public TintCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!this.isInEditMode()) {
            TintManager tintManager = TintManager.get(context);
            this.mBackgroundHelper = new AppCompatBackgroundHelper(this, tintManager);
            this.mBackgroundHelper.loadFromAttribute(attrs, 0);
        }
    }

    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.setBackgroundDrawableExternal(background);
        }

    }

    public void setBackgroundResource(int resId) {
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.setBackgroundResId(resId);
        } else {
            super.setBackgroundResource(resId);
        }

    }

    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.setBackgroundColor(color);
        }

    }

    public void setBackgroundTintList(int resId) {
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.setBackgroundTintList(resId, (PorterDuff.Mode)null);
        }

    }

    public void setBackgroundTintList(int resId, PorterDuff.Mode mode) {
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.setBackgroundTintList(resId, mode);
        }

    }

    public void tint() {
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.tint();
        }

    }
}
