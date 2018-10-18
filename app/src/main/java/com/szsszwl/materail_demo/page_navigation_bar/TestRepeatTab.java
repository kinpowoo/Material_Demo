package com.szsszwl.materail_demo.page_navigation_bar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.szsszwl.materail_demo.R;

/**
 * Created by mjj on 2017/10/19
 * <p>
 *     测试重复点击的触发
 * </p>
 */
public class TestRepeatTab extends OnlyIconItemView{

    public TestRepeatTab(Context context) {
        super(context);
    }

    public TestRepeatTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestRepeatTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ObjectAnimator mAnimator;

    @Override
    public void onRepeat() {
        super.onRepeat();

        if (mAnimator == null){
            ObjectAnimator animator = ObjectAnimator.ofFloat((View)findViewById(R.id.icon),"Rotation",0f,-360f);
            animator.setDuration(375);
            mAnimator = animator;
        }

        if (!mAnimator.isStarted()){
            mAnimator.start();
        }

    }
}
