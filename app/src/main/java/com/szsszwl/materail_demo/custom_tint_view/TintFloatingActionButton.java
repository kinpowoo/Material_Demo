package com.szsszwl.materail_demo.custom_tint_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.bilibili.magicasakura.utils.TintManager;
import com.bilibili.magicasakura.widgets.Tintable;

/**
 * Created by DeskTop29 on 2018/10/18.
 */

@SuppressLint({"AppCompatCustomView"})
public class TintFloatingActionButton extends FloatingActionButton implements Tintable, AppCompatBackgroundHelper.BackgroundExtensible, AppCompatImageHelper.ImageExtensible {
    public AppCompatBackgroundHelper mBackgroundHelper;
    private AppCompatImageHelper mImageHelper;

    public TintFloatingActionButton(Context context) {
        this(context, (AttributeSet)null);
    }

    public TintFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TintFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!this.isInEditMode()) {
            TintManager tintManager = TintManager.get(context);
            this.mBackgroundHelper = new AppCompatBackgroundHelper(this, tintManager);
            this.mBackgroundHelper.loadFromAttribute(attrs, defStyleAttr);
            this.mImageHelper = new AppCompatImageHelper(this, tintManager);
            this.mImageHelper.loadFromAttribute(attrs, defStyleAttr);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.getBackground() != null) {
            this.invalidateDrawable(this.getBackground());
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

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if(this.mImageHelper != null) {
            this.mImageHelper.setImageDrawable();
        }

    }

    public void setImageResource(int resId) {
        if(this.mImageHelper != null) {
            this.mImageHelper.setImageResId(resId);
        } else {
            super.setImageResource(resId);
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

    public void setImageTintList(int resId) {
        if(this.mImageHelper != null) {
            this.mImageHelper.setImageTintList(resId, (PorterDuff.Mode)null);
        }

    }

    public void setImageTintList(int resId, PorterDuff.Mode mode) {
        if(this.mImageHelper != null) {
            this.mImageHelper.setImageTintList(resId, mode);
        }

    }

    public void tint() {
        if(this.mBackgroundHelper != null) {
            this.mBackgroundHelper.tint();
        }

        if(this.mImageHelper != null) {
            this.mImageHelper.tint();
        }

    }
}
