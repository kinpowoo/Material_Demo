package com.szsszwl.materail_demo.custom_tint_view;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.View;

import com.bilibili.magicasakura.utils.DrawableUtils;
import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.utils.TintInfo;
import com.bilibili.magicasakura.utils.TintManager;


/**
 * Created by DeskTop29 on 2018/10/18.
 */

public class AppCompatBackgroundHelper extends AppCompatBaseHelper<View> {
    private TintInfo mBackgroundTintInfo;
    private int mBackgroundResId;
    private int mBackgroundTintResId;
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    public AppCompatBackgroundHelper(View view, TintManager tintManager) {
        super(view, tintManager);
    }

    void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        this.initPadding();
        TypedArray array = this.mView.getContext().obtainStyledAttributes(attrs, com.bilibili.magicasakura.R.styleable.TintViewBackgroundHelper, defStyleAttr, 0);
        if(array.hasValue(com.bilibili.magicasakura.R.styleable.TintViewBackgroundHelper_backgroundTint)) {
            this.mBackgroundTintResId = array.getResourceId(com.bilibili.magicasakura.R.styleable.TintViewBackgroundHelper_backgroundTint, 0);
            if(array.hasValue(com.bilibili.magicasakura.R.styleable.TintViewBackgroundHelper_backgroundTintMode)) {
                this.setSupportBackgroundTintMode(DrawableUtils.parseTintMode(array.getInt(com.bilibili.magicasakura.R.styleable.TintViewBackgroundHelper_backgroundTintMode, 0), (PorterDuff.Mode)null));
            }

            this.setSupportBackgroundTint(this.mBackgroundTintResId);
        } else {
            Drawable drawable = this.mTintManager.getDrawable(this.mBackgroundResId = array.getResourceId(com.bilibili.magicasakura.R.styleable.TintViewBackgroundHelper_android_background, 0));
            if(drawable != null) {
                this.setBackgroundDrawable(drawable);
            }
        }

        array.recycle();
    }

    public void setBackgroundDrawableExternal(Drawable background) {
        if(!this.skipNextApply()) {
            this.resetTintResource(0);
            this.setSkipNextApply(false);
            this.recoverPadding(background);
        }
    }

    public void setBackgroundColor(int color) {
        if(!this.skipNextApply()) {
            this.resetTintResource(0);
            this.mView.setBackgroundColor(ThemeUtils.getColor(this.mView.getContext(), color));
        }
    }

    public void setBackgroundResId(int resId) {
        if(this.mBackgroundResId != resId) {
            this.resetTintResource(resId);
            if(resId != 0) {
                Drawable drawable = this.mTintManager.getDrawable(resId);
                this.setBackgroundDrawable(drawable != null?drawable: ContextCompat.getDrawable(this.mView.getContext(), resId));
            }
        }

    }

    public void setBackgroundTintList(int resId, PorterDuff.Mode mode) {
        if(this.mBackgroundTintResId != resId) {
            this.mBackgroundTintResId = resId;
            if(this.mBackgroundTintInfo != null) {
                this.mBackgroundTintInfo.mHasTintList = false;
                this.mBackgroundTintInfo.mTintList = null;
            }

            this.setSupportBackgroundTintMode(mode);
            this.setSupportBackgroundTint(resId);
        }

    }

    private void setBackgroundDrawable(Drawable drawable) {
        if(!this.skipNextApply()) {
            this.setBackground(drawable);
            this.recoverPadding(drawable);
        }
    }

    private boolean setSupportBackgroundTint(int resId) {
        if(resId != 0) {
            if(this.mBackgroundTintInfo == null) {
                this.mBackgroundTintInfo = new TintInfo();
            }

            this.mBackgroundTintInfo.mHasTintList = true;
            this.mBackgroundTintInfo.mTintList = this.mTintManager.getColorStateList(resId);
        }

        return this.applySupportBackgroundTint();
    }

    private void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if(this.mBackgroundTintResId != 0 && mode != null) {
            if(this.mBackgroundTintInfo == null) {
                this.mBackgroundTintInfo = new TintInfo();
            }

            this.mBackgroundTintInfo.mHasTintMode = true;
            this.mBackgroundTintInfo.mTintMode = mode;
        }

    }

    private boolean applySupportBackgroundTint() {
        Drawable backgroundDrawable = this.mView.getBackground();
        if(backgroundDrawable != null && this.mBackgroundTintInfo != null && this.mBackgroundTintInfo.mHasTintList) {
            backgroundDrawable = DrawableCompat.wrap(backgroundDrawable);
            backgroundDrawable = backgroundDrawable.mutate();
            if(this.mBackgroundTintInfo.mHasTintList) {
                DrawableCompat.setTintList(backgroundDrawable, this.mBackgroundTintInfo.mTintList);
            }

            if(this.mBackgroundTintInfo.mHasTintMode) {
                DrawableCompat.setTintMode(backgroundDrawable, this.mBackgroundTintInfo.mTintMode);
            }

            if(backgroundDrawable.isStateful()) {
                backgroundDrawable.setState(this.mView.getDrawableState());
            }

            this.setBackgroundDrawable(backgroundDrawable);
            return true;
        } else {
            return false;
        }
    }

    private void setBackground(Drawable backgroundDrawable) {
        if(Build.VERSION.SDK_INT < 16) {
            this.mView.setBackgroundDrawable(backgroundDrawable);
        } else {
            this.mView.setBackground(backgroundDrawable);
        }

    }

    private void resetTintResource(int resId) {
        this.mBackgroundResId = resId;
        this.mBackgroundTintResId = 0;
        if(this.mBackgroundTintInfo != null) {
            this.mBackgroundTintInfo.mHasTintList = false;
            this.mBackgroundTintInfo.mTintList = null;
            this.mBackgroundTintInfo.mHasTintMode = false;
            this.mBackgroundTintInfo.mTintMode = null;
        }

    }

    private void initPadding() {
        this.mPaddingLeft = this.mView.getPaddingLeft();
        this.mPaddingTop = this.mView.getPaddingTop();
        this.mPaddingRight = this.mView.getPaddingRight();
        this.mPaddingBottom = this.mView.getPaddingBottom();
    }

    private boolean hasPadding() {
        return this.mPaddingLeft != 0 || this.mPaddingRight != 0 || this.mPaddingTop != 0 || this.mPaddingBottom != 0;
    }

    private void recoverPadding(Drawable background) {
        if(ThemeUtils.containsNinePatch(background) && this.hasPadding()) {
            this.mView.setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
        }

    }

    public void tint() {
        if(this.mBackgroundTintResId == 0 || !this.setSupportBackgroundTint(this.mBackgroundTintResId)) {
            Drawable drawable = this.mTintManager.getDrawable(this.mBackgroundResId);
            if(drawable == null) {
                drawable = this.mBackgroundResId == 0?null:ContextCompat.getDrawable(this.mView.getContext(), this.mBackgroundResId);
            }

            this.setBackgroundDrawable(drawable);
        }

    }

    public interface BackgroundExtensible {
        void setBackgroundTintList(int var1);

        void setBackgroundTintList(int var1, PorterDuff.Mode var2);
    }
}