package com.szsszwl.materail_demo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by DeskTop29 on 2018/10/25.
 */

public class ColorStateListUtil {

    private ColorStateList createColorStateList(int normal, int pressed) {
        return createColorStateList(normal, pressed, 0xff0000ff, 0xffff0000);
    }

    //生成ColorStateList对象，实际上是一个selector的drawable对象
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }


    //从XML文件中获取ColorStateList对象
    public ColorStateList getColorStateListForXML(Context c,int xmlSelectorID){
        Resources resource = c.getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(xmlSelectorID);
        if(csl!=null){
            return csl;
        }
        return null;
    }

    public static ColorStateList createColorStateListForXML(Context c,int xmlSelectorID) {
        XmlResourceParser xpp=Resources.getSystem().getXml(xmlSelectorID);
        try {
            ColorStateList csl= ColorStateList.createFromXml(c.getResources(),xpp);
            return csl;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }


    //生成StateListDrawable对象,实际上是selector drawable
    public static StateListDrawable newSelector(Context context, int idNormal, int idPressed,
                                                int idFocused, int idUnable) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
        Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
        Drawable focused = idFocused == -1 ? null : context.getResources().getDrawable(idFocused);
        Drawable unable = idUnable == -1 ? null : context.getResources().getDrawable(idUnable);
        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
        // View.ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled }, normal);
        // View.FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_focused }, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_window_focused }, unable);
        // View.EMPTY_STATE_SET
        bg.addState(new int[] {}, normal);
        return bg;
    }
}
