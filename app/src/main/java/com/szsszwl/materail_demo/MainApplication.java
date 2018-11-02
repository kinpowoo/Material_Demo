package com.szsszwl.materail_demo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatDelegate;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.szsszwl.materail_demo.sakura.ThemeHelper;

/**
 * Created by DeskTop29 on 2018/10/15.
 */

public class MainApplication extends Application implements ThemeUtils.switchColor{

    @Override
    public void onCreate() {
        super.onCreate();

        ThemeUtils.setSwitchColor(this);
    }

    @Override
    public int replaceColorById(Context context, int colorId) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return context.getResources().getColor(colorId);
        }
        String theme = ThemeHelper.getThemeName(context);
        if (theme != null) {
            colorId = ThemeHelper.getThemeColorIdByColorId(context, colorId, theme);
        }
        return context.getResources().getColor(colorId);
    }

    @Override
    public int replaceColor(Context context, int originColor) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return originColor;
        }
        String theme = ThemeHelper.getThemeName(context);
        int colorId = -1;

        if (theme != null) {
            colorId = ThemeHelper.getThemeColorIdByHexColor(context, originColor, theme);
        }
        return colorId != -1 ? getResources().getColor(colorId) : originColor;
    }


}
