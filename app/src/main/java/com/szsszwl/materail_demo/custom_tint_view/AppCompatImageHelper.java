package com.szsszwl.materail_demo.custom_tint_view;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bilibili.magicasakura.utils.DrawableUtils;
import com.bilibili.magicasakura.utils.TintInfo;
import com.bilibili.magicasakura.utils.TintManager;

/**
 * Created by DeskTop29 on 2018/10/18.
 */

public class AppCompatImageHelper extends AppCompatBaseHelper<ImageView> {
    private TintInfo mImageTintInfo;
    private int mImageResId;
    private int mImageTintResId;

    public AppCompatImageHelper(ImageView view, TintManager tintManager) {
        super(view, tintManager);
    }

    void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray array = ((ImageView)this.mView).getContext().obtainStyledAttributes(attrs, com.bilibili.magicasakura.R.styleable.TintImageHelper, defStyleAttr, 0);
        Drawable image;
        if(((ImageView)this.mView).getDrawable() == null) {
            image = this.mTintManager.getDrawable(this.mImageResId = array.getResourceId(com.bilibili.magicasakura.R.styleable.TintImageHelper_srcCompat, 0));
            if(image != null) {
                this.setImageDrawable(image);
            }
        }

        if(array.hasValue(com.bilibili.magicasakura.R.styleable.TintImageHelper_imageTint)) {
            this.mImageTintResId = array.getResourceId(com.bilibili.magicasakura.R.styleable.TintImageHelper_imageTint, 0);
            if(array.hasValue(com.bilibili.magicasakura.R.styleable.TintImageHelper_imageTintMode)) {
                this.setSupportImageTintMode(DrawableUtils.parseTintMode(array.getInt(com.bilibili.magicasakura.R.styleable.TintImageHelper_imageTintMode, 0), (PorterDuff.Mode)null));
            }

            this.setSupportImageTint(this.mImageTintResId);
        } else if(this.mImageResId == 0) {
            image = this.mTintManager.getDrawable(this.mImageResId = array.getResourceId(com.bilibili.magicasakura.R.styleable.TintImageHelper_android_src, 0));
            if(image != null) {
                this.setImageDrawable(image);
            }
        }

        array.recycle();
    }

    public void setImageDrawable() {
        if(!this.skipNextApply()) {
            this.resetTintResource(0);
            this.setSkipNextApply(false);
        }
    }

    public void setImageResId(int resId) {
        if(this.mImageResId != resId) {
            this.resetTintResource(resId);
            if(resId != 0) {
                Drawable image = this.mTintManager.getDrawable(resId);
                this.setImageDrawable(image != null?image: ContextCompat.getDrawable(((ImageView)this.mView).getContext(), resId));
            }
        }

    }

    public void setImageTintList(int resId, PorterDuff.Mode mode) {
        if(this.mImageTintResId != resId) {
            this.mImageTintResId = resId;
            if(this.mImageTintInfo != null) {
                this.mImageTintInfo.mHasTintList = false;
                this.mImageTintInfo.mTintList = null;
            }

            this.setSupportImageTintMode(mode);
            this.setSupportImageTint(resId);
        }

    }

    private void setImageDrawable(Drawable drawable) {
        if(!this.skipNextApply()) {
            ((ImageView)this.mView).setImageDrawable(drawable);
        }
    }

    private boolean setSupportImageTint(int resId) {
        if(resId != 0) {
            if(this.mImageTintInfo == null) {
                this.mImageTintInfo = new TintInfo();
            }

            this.mImageTintInfo.mHasTintList = true;
            this.mImageTintInfo.mTintList = this.mTintManager.getColorStateList(resId);
        }

        return this.applySupportImageTint();
    }

    private void setSupportImageTintMode(PorterDuff.Mode mode) {
        if(this.mImageTintResId != 0 && mode != null) {
            if(this.mImageTintInfo == null) {
                this.mImageTintInfo = new TintInfo();
            }

            this.mImageTintInfo.mHasTintMode = true;
            this.mImageTintInfo.mTintMode = mode;
        }

    }

    private boolean applySupportImageTint() {
        Drawable image = ((ImageView)this.mView).getDrawable();
        if(image != null && this.mImageTintInfo != null && this.mImageTintInfo.mHasTintList) {
            Drawable tintDrawable = image.mutate();
            tintDrawable = DrawableCompat.wrap(tintDrawable);
            if(this.mImageTintInfo.mHasTintList) {
                DrawableCompat.setTintList(tintDrawable, this.mImageTintInfo.mTintList);
            }

            if(this.mImageTintInfo.mHasTintMode) {
                DrawableCompat.setTintMode(tintDrawable, this.mImageTintInfo.mTintMode);
            }

            if(tintDrawable.isStateful()) {
                tintDrawable.setState(((ImageView)this.mView).getDrawableState());
            }

            this.setImageDrawable(tintDrawable);
            if(image == tintDrawable) {
                tintDrawable.invalidateSelf();
            }

            return true;
        } else {
            return false;
        }
    }

    private void resetTintResource(int resId) {
        this.mImageResId = resId;
        this.mImageTintResId = 0;
        if(this.mImageTintInfo != null) {
            this.mImageTintInfo.mHasTintList = false;
            this.mImageTintInfo.mTintList = null;
            this.mImageTintInfo.mHasTintMode = false;
            this.mImageTintInfo.mTintMode = null;
        }

    }

    public void tint() {
        if(this.mImageTintResId == 0 || !this.setSupportImageTint(this.mImageTintResId)) {
            Drawable drawable = this.mTintManager.getDrawable(this.mImageResId);
            if(drawable == null) {
                drawable = this.mImageResId == 0?null:ContextCompat.getDrawable(((ImageView)this.mView).getContext(), this.mImageResId);
            }

            this.setImageDrawable(drawable);
        }

    }

    public interface ImageExtensible {
        void setImageTintList(int var1);

        void setImageTintList(int var1, PorterDuff.Mode var2);
    }
}
