package com.szsszwl.materail_demo.sakura;

/**
 * Created by DeskTop29 on 2018/10/15.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.view.View;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.szsszwl.materail_demo.R;


/**
 * @author xyczero
 * @time 16/5/2
 */
public class ThemeHelper {
    protected static final String CURRENT_THEME = "theme_current";
    protected static final String DAY_THEME = "theme_day";

    public static final int CARD_RED = 0x1;
    public static final int CARD_PINK = 0x2;
    public static final int CARD_PURPLE = 0x3;
    public static final int CARD_DEEP_PURPLE = 0x4;
    public static final int CARD_INDIGO = 0x5;
    public static final int CARD_BLUE = 0x6;
    public static final int CARD_LIGHT_BLUE = 0x7;
    public static final int CARD_CYAN = 0x8;
    public static final int CARD_TEAL = 0x9;
    public static final int CARD_GREEN = 0x10;
    public static final int CARD_LIGHT_GREEN = 0x11;
    public static final int CARD_LIME = 0x12;
    public static final int CARD_YELLOW = 0x13;
    public static final int CARD_AMBER = 0x14;
    public static final int CARD_ORANGE = 0x15;
    public static final int CARD_DEEP_ORANGE = 0x16;
    public static final int CARD_BROWN = 0x17;
    public static final int CARD_GREY = 0x18;
    public static final int CARD_BLUE_GREY = 0x19;
    public static final int CARD_BLACK = 0x20;

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("multiple_theme", Context.MODE_PRIVATE);
    }

    public static void setTheme(Context context, int themeId) {
        getSharePreference(context).edit()
                .putInt(CURRENT_THEME, themeId)
                .commit();
    }

    public static void setDayTheme(Context context, int themeId) {
        getSharePreference(context).edit()
                .putInt(DAY_THEME, themeId)
                .commit();
    }

    public static int getTheme(Context context) {
        return getSharePreference(context).getInt(CURRENT_THEME, CARD_PINK);
    }

    public static int getDayTheme(Context context) {
        return getSharePreference(context).getInt(DAY_THEME, CARD_PINK);
    }

    public static boolean isDefaultTheme(Context context) {
        return getTheme(context) == CARD_PINK;
    }






    public static int getPrimaryColorByThemeId(Context context, int themeId){
        String themeName = getThemeName(themeId);
        if(themeName!=null) {
            int colorId = getThemeColorIdByColorId(context, R.color.theme_color_primary, themeName);
            return context.getResources().getColor(colorId);
        }
        return R.color.theme_color_primary;
    }


    public static int getPrimaryDarkColorByThemeId(Context context, int themeId){
        String themeName = getThemeName(themeId);
        if(themeName!=null) {
            int colorId = getThemeColorIdByColorId(context, R.color.theme_color_primary_dark, themeName);
            return context.getResources().getColor(colorId);
        }
        return R.color.theme_color_primary_dark;
    }


    public static int getAccentColorByThemeId(Context context, int themeId) {
        String themeName = getThemeName(themeId);
        if (themeName != null) {
            int colorId = getThemeColorIdByColorId(context, R.color.theme_color_accent, themeName);
            return context.getResources().getColor(colorId);
        }
        return R.color.theme_color_accent;
    }

    public static int getPrimaryColorLightByThemeId(Context context, int themeId) {
        String themeName = getThemeName(themeId);
        if (themeName != null) {
            int colorId = getThemeColorIdByColorId(context, R.color.theme_color_primary_light, themeName);
            return context.getResources().getColor(colorId);
        }
        return R.color.theme_color_primary_light;
    }



    public static String getThemeName(Context context) {
        if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_RED) {
            return "red";
        }else if(ThemeHelper.getTheme(context) == ThemeHelper.CARD_PINK) {
            return "pink";
        }else if(ThemeHelper.getTheme(context) == ThemeHelper.CARD_PURPLE) {
            return "purple";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_DEEP_PURPLE) {
            return "deep_purple";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_INDIGO) {
            return "indigo";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_BLUE) {
            return "blue";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT_BLUE) {
            return "light_blue";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_CYAN) {
            return "cyan";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_TEAL) {
            return "teal";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_GREEN) {
            return "green";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT_GREEN) {
            return "light_green";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIME) {
            return "lime";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_YELLOW) {
            return "yellow";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_AMBER) {
            return "amber";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_ORANGE) {
            return "orange";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_DEEP_ORANGE) {
            return "deep_orange";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_BROWN) {
            return "brown";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_GREY) {
            return "grey";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_BLUE_GREY) {
            return "blue_grey";
        }else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_BLACK) {
            return "black";
        }
        return null;
    }



    public static String getThemeName(int themeId) {
        if (themeId == ThemeHelper.CARD_RED) {
            return "red";
        }else if(themeId == ThemeHelper.CARD_PINK) {
            return "pink";
        }else if(themeId == ThemeHelper.CARD_PURPLE) {
            return "purple";
        }else if (themeId == ThemeHelper.CARD_DEEP_PURPLE) {
            return "deep_purple";
        }else if (themeId == ThemeHelper.CARD_INDIGO) {
            return "indigo";
        }else if (themeId == ThemeHelper.CARD_BLUE) {
            return "blue";
        }else if (themeId == ThemeHelper.CARD_LIGHT_BLUE) {
            return "light_blue";
        }else if (themeId == ThemeHelper.CARD_CYAN) {
            return "cyan";
        }else if (themeId == ThemeHelper.CARD_TEAL) {
            return "teal";
        } else if (themeId == ThemeHelper.CARD_GREEN) {
            return "green";
        } else if (themeId == ThemeHelper.CARD_LIGHT_GREEN) {
            return "light_green";
        } else if (themeId == ThemeHelper.CARD_LIME) {
            return "lime";
        } else if (themeId == ThemeHelper.CARD_YELLOW) {
            return "yellow";
        }else if (themeId == ThemeHelper.CARD_AMBER) {
            return "amber";
        } else if (themeId == ThemeHelper.CARD_ORANGE) {
            return "orange";
        }else if (themeId == ThemeHelper.CARD_DEEP_ORANGE) {
            return "deep_orange";
        }else if (themeId == ThemeHelper.CARD_BROWN) {
            return "brown";
        }else if (themeId == ThemeHelper.CARD_GREY) {
            return "grey";
        } else if (themeId == ThemeHelper.CARD_BLUE_GREY) {
            return "blue_grey";
        }else if (themeId == ThemeHelper.CARD_BLACK) {
            return "black";
        }
        return null;
    }



    //通过R.id.theme_color_primary 的形式来获取对应Theme的color的 colorId
    public static
    @ColorRes
    int getThemeColorIdByColorId(Context context, int colorId, String theme) {
        switch (colorId) {
            case R.color.theme_color_primary:
                return context.getResources().getIdentifier(theme + "_primary", "color", context.getPackageName());
            case R.color.theme_color_primary_dark:
                return context.getResources().getIdentifier(theme + "_primary_dark", "color",context.getPackageName());
            case R.color.theme_color_primary_light:
                return context.getResources().getIdentifier(theme + "_primary_light", "color", context.getPackageName());
            case R.color.theme_color_accent:
                return context.getResources().getIdentifier(theme + "_accent", "color", context.getPackageName());
            case R.color.theme_color_bg:
                return context.getResources().getIdentifier(theme + "_bg", "color", context.getPackageName());
        }
        return colorId;
    }


    //通过0xffe91e63 十六进制的形式来获取对应Theme的color的 colorId
    public static
    @ColorRes
    int getThemeColorIdByHexColor(Context context, int hexColor, String theme) {
        switch (hexColor) {
            case 0xffE91E63:
                return context.getResources().getIdentifier(theme +"_primary", "color",context.getPackageName());
            case 0xffC2185B:
                return context.getResources().getIdentifier(theme + "_primary_dark", "color",context.getPackageName());
            case 0xffF8BBD0:
                return context.getResources().getIdentifier(theme + "_primary_light", "color", context.getPackageName());
            case 0xff4CAF50:
                return context.getResources().getIdentifier(theme + "_accent", "color",context.getPackageName());
            case 0xffffffff:
                return context.getResources().getIdentifier(theme + "_bg", "color", context.getPackageName());
        }
        return -1;
    }



    public static void refreshTheme(final Activity context) {
        ThemeUtils.refreshUI(context, new ThemeUtils.ExtraRefreshable() {
                    @Override
                    public void refreshGlobal(Activity activity) {
                        //for global setting, just do once
                        if (Build.VERSION.SDK_INT >= 21) {

                            ActivityManager.TaskDescription taskDescription =
                                    new ActivityManager.TaskDescription(null, null,
                                            ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                            context.setTaskDescription(taskDescription);
                            context.getWindow().setStatusBarColor(
                                    ThemeUtils.getColorById(context, R.color.theme_color_primary));
                        }
                    }

                    @Override
                    public void refreshSpecificView(View view) {
                        //这里会对所有view进行遍历
                    }
                }
        );
    }
}
