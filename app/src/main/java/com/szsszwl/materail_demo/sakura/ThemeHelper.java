package com.szsszwl.materail_demo.sakura;

/**
 * Created by DeskTop29 on 2018/10/15.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.szsszwl.materail_demo.R;


/**
 * @author xyczero
 * @time 16/5/2
 */
public class ThemeHelper {
    private static final String CURRENT_THEME = "theme_current";

    public static final int CARD_PINK = 0x1;
    public static final int CARD_PURPLE = 0x2;
    public static final int CARD_BLUE = 0x3;
    public static final int CARD_GREEN = 0x4;
    public static final int CARD_GREEN_LIGHT = 0x5;
    public static final int CARD_YELLOW = 0x6;
    public static final int CARD_ORANGE = 0x7;
    public static final int CARD_RED = 0x8;

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("multiple_theme", Context.MODE_PRIVATE);
    }

    public static void setTheme(Context context, int themeId) {
        getSharePreference(context).edit()
                .putInt(CURRENT_THEME, themeId)
                .commit();
    }

    public static int getTheme(Context context) {
        return getSharePreference(context).getInt(CURRENT_THEME, CARD_PINK);
    }

    public static boolean isDefaultTheme(Context context) {
        return getTheme(context) == CARD_PINK;
    }

    public static String getName(int currentTheme) {
        switch (currentTheme) {
            case CARD_PINK:
                return "THE SAKURA";
            case CARD_BLUE:
                return "THE STORM";
            case CARD_GREEN:
                return "THE WOOD";
            case CARD_GREEN_LIGHT:
                return "THE LIGHT";
            case CARD_PURPLE:
                return "THE HOPE";
            case CARD_YELLOW:
                return "THE THUNDER";
            case CARD_ORANGE:
                return "THE SAND";
            case CARD_RED:
                return "THE FIREY";
        }
        return "THE RETURN";
    }


    public static int getCurrentPrimaryColor(Context context){
        int currentTheme =  getTheme(context);
        switch (currentTheme){
            case ThemeHelper.CARD_PINK:
                return context.getResources().getColor(R.color.pink);
            case ThemeHelper.CARD_BLUE:
                return context.getResources().getColor(R.color.blue);
            case ThemeHelper.CARD_GREEN:
                return context.getResources().getColor(R.color.green);
            case ThemeHelper.CARD_YELLOW:
                return context.getResources().getColor(R.color.yellow);
            case ThemeHelper.CARD_PURPLE:
                return context.getResources().getColor(R.color.purple);
            case ThemeHelper.CARD_GREEN_LIGHT:
                return context.getResources().getColor(R.color.green_light);
            case ThemeHelper.CARD_ORANGE:
                return context.getResources().getColor(R.color.orange);
            case ThemeHelper.CARD_RED:
                return context.getResources().getColor(R.color.red);
        }
        return context.getResources().getColor(R.color.pink);
    }

    public static int getCurrentPrimaryDarkColor(Context context){
        int currentTheme =  getTheme(context);
        switch (currentTheme){
            case ThemeHelper.CARD_PINK:
                return context.getResources().getColor(R.color.pink_dark);
            case ThemeHelper.CARD_BLUE:
                return context.getResources().getColor(R.color.blue_dark);
            case ThemeHelper.CARD_GREEN:
                return context.getResources().getColor(R.color.green_dark);
            case ThemeHelper.CARD_YELLOW:
                return context.getResources().getColor(R.color.yellow_dark);
            case ThemeHelper.CARD_PURPLE:
                return context.getResources().getColor(R.color.purple_dark);
            case ThemeHelper.CARD_GREEN_LIGHT:
                return context.getResources().getColor(R.color.green_light_dark);
            case ThemeHelper.CARD_ORANGE:
                return context.getResources().getColor(R.color.orange_dark);
            case ThemeHelper.CARD_RED:
                return context.getResources().getColor(R.color.red_dark);
        }
        return context.getResources().getColor(R.color.pink_dark);
    }


    public static int getCurrentTransColor(Context context){
        int currentTheme =  getTheme(context);
        switch (currentTheme){
            case ThemeHelper.CARD_PINK:
                return context.getResources().getColor(R.color.pink_trans);
            case ThemeHelper.CARD_BLUE:
                return context.getResources().getColor(R.color.blue_trans);
            case ThemeHelper.CARD_GREEN:
                return context.getResources().getColor(R.color.green_trans);
            case ThemeHelper.CARD_YELLOW:
                return context.getResources().getColor(R.color.yellow_trans);
            case ThemeHelper.CARD_PURPLE:
                return context.getResources().getColor(R.color.purple_trans);
            case ThemeHelper.CARD_GREEN_LIGHT:
                return context.getResources().getColor(R.color.green_light_trans);
            case ThemeHelper.CARD_ORANGE:
                return context.getResources().getColor(R.color.orange_trans);
            case ThemeHelper.CARD_RED:
                return context.getResources().getColor(R.color.red_trans);
        }
        return context.getResources().getColor(R.color.pink_trans);
    }
}
