package com.szsszwl.materail_demo.sakura;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.szsszwl.materail_demo.R;

/**
 * Created by DeskTop29 on 2018/10/25.
 */

public class ColorPickerActivity extends BaseActivity implements View.OnClickListener {

    protected int mCurrentTheme;
    ImageView[] mCards = new ImageView[19];
    SwitchCompat nightModeSwitch;
    Toolbar toolbar;
    LinearLayout nightModeArea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker_activity);
        nightModeSwitch = (SwitchCompat) findViewById(R.id.night_mode_on);
        nightModeArea = (LinearLayout) findViewById(R.id.night_mode_area);

        toolbar = setupToolbar(new BackCallBack() {
            @Override
            public void beforeBack() {
                setResult(RESULT_CANCELED);
            }
        });

        mCards[0] = (ImageView) findViewById(R.id.theme_red);
        mCards[1] = (ImageView) findViewById(R.id.theme_pink);
        mCards[2] = (ImageView) findViewById(R.id.theme_purple);
        mCards[3] = (ImageView) findViewById(R.id.theme_deep_purple);
        mCards[4] = (ImageView) findViewById(R.id.theme_indigo);
        mCards[5] = (ImageView) findViewById(R.id.theme_blue);
        mCards[6] = (ImageView) findViewById(R.id.theme_light_blue);
        mCards[7] = (ImageView) findViewById(R.id.theme_cyan);
        mCards[8] = (ImageView) findViewById(R.id.theme_teal);
        mCards[9] = (ImageView) findViewById(R.id.theme_green);
        mCards[10] = (ImageView) findViewById(R.id.theme_light_green);
        mCards[11] = (ImageView) findViewById(R.id.theme_lime);
        mCards[12] = (ImageView) findViewById(R.id.theme_yellow);
        mCards[13] = (ImageView) findViewById(R.id.theme_amber);
        mCards[14] = (ImageView) findViewById(R.id.theme_orange);
        mCards[15] = (ImageView) findViewById(R.id.theme_deep_orange);
        mCards[16] = (ImageView) findViewById(R.id.theme_brown);
        mCards[17] = (ImageView) findViewById(R.id.theme_grey);
        mCards[18] = (ImageView) findViewById(R.id.theme_blue_grey);


        //初始化主题
        mCurrentTheme = ThemeHelper.getTheme(this);
        changeToolbarAndNightModeAreaColor(mCurrentTheme);
        if(mCurrentTheme == ThemeHelper.CARD_BLACK){
            nightModeSwitch.setChecked(true);
            for(ImageView v:mCards){
                v.setEnabled(false);
                v.setSelected(false);
            }
        }else {
            setImageButtons(mCurrentTheme);
        }

        //设置监听
        for (ImageView card : mCards) {
            card.setOnClickListener(this);
        }



        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(mCurrentTheme!=ThemeHelper.CARD_BLACK) {
                        ThemeHelper.setDayTheme(ColorPickerActivity.this,mCurrentTheme);
                        ThemeHelper.setTheme(ColorPickerActivity.this,ThemeHelper.CARD_BLACK);
                        mCurrentTheme = ThemeHelper.CARD_BLACK;
                        changeToolbarAndNightModeAreaColor(ThemeHelper.CARD_BLACK);

                        for(ImageView v:mCards){
                            v.setEnabled(false);
                            v.setSelected(false);
                        }
                    }else{
                        for(ImageView v:mCards){
                            v.setEnabled(false);
                            v.setSelected(false);
                        }
                    }
                }else{
                    int dayThemeId = ThemeHelper.getDayTheme(ColorPickerActivity.this);
                    changeToolbarAndNightModeAreaColor(dayThemeId);

                    mCurrentTheme = dayThemeId;
                    ThemeHelper.setTheme(ColorPickerActivity.this,mCurrentTheme);

                    setImageButtons(mCurrentTheme);
                    for(ImageView v:mCards){
                        v.setEnabled(true);
                    }
                }
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.theme_red:
                mCurrentTheme = ThemeHelper.CARD_RED;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_pink:
                mCurrentTheme = ThemeHelper.CARD_PINK;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_purple:
                mCurrentTheme = ThemeHelper.CARD_PURPLE;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_deep_purple:
                mCurrentTheme = ThemeHelper.CARD_DEEP_PURPLE;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_indigo:
                mCurrentTheme = ThemeHelper.CARD_INDIGO;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_blue:
                mCurrentTheme = ThemeHelper.CARD_BLUE;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_light_blue:
                mCurrentTheme = ThemeHelper.CARD_LIGHT_BLUE;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_cyan:
                mCurrentTheme = ThemeHelper.CARD_CYAN;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_teal:
                mCurrentTheme = ThemeHelper.CARD_TEAL;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_green:
                mCurrentTheme = ThemeHelper.CARD_GREEN;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_light_green:
                mCurrentTheme = ThemeHelper.CARD_LIGHT_GREEN;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_lime:
                mCurrentTheme = ThemeHelper.CARD_LIME;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_yellow:
                mCurrentTheme = ThemeHelper.CARD_YELLOW;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_amber:
                mCurrentTheme = ThemeHelper.CARD_AMBER;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_orange:
                mCurrentTheme = ThemeHelper.CARD_ORANGE;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_deep_orange:
                mCurrentTheme = ThemeHelper.CARD_DEEP_ORANGE;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_brown:
                mCurrentTheme = ThemeHelper.CARD_BROWN;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_grey:
                mCurrentTheme = ThemeHelper.CARD_GREY;
                setImageButtons(mCurrentTheme);
                break;
            case R.id.theme_blue_grey:
                mCurrentTheme = ThemeHelper.CARD_BLUE_GREY;
                setImageButtons(mCurrentTheme);
                break;
            default:
                break;
        }
    }

    private void setImageButtons(int currentTheme) {
        Log.i("tag","current_theme_name: "+ThemeHelper.getThemeName(currentTheme));

        changeToolbarAndNightModeAreaColor(currentTheme);

        mCards[0].setSelected(currentTheme == ThemeHelper.CARD_RED);
        mCards[1].setSelected(currentTheme == ThemeHelper.CARD_PINK);
        mCards[2].setSelected(currentTheme == ThemeHelper.CARD_PURPLE);
        mCards[3].setSelected(currentTheme == ThemeHelper.CARD_DEEP_PURPLE);
        mCards[4].setSelected(currentTheme == ThemeHelper.CARD_INDIGO);
        mCards[5].setSelected(currentTheme == ThemeHelper.CARD_BLUE);
        mCards[6].setSelected(currentTheme == ThemeHelper.CARD_LIGHT_BLUE);
        mCards[7].setSelected(currentTheme == ThemeHelper.CARD_CYAN);
        mCards[8].setSelected(currentTheme == ThemeHelper.CARD_TEAL);
        mCards[9].setSelected(currentTheme == ThemeHelper.CARD_GREEN);
        mCards[10].setSelected(currentTheme == ThemeHelper.CARD_LIGHT_GREEN);
        mCards[11].setSelected(currentTheme == ThemeHelper.CARD_LIME);
        mCards[12].setSelected(currentTheme == ThemeHelper.CARD_YELLOW);
        mCards[13].setSelected(currentTheme == ThemeHelper.CARD_AMBER);
        mCards[14].setSelected(currentTheme == ThemeHelper.CARD_ORANGE);
        mCards[15].setSelected(currentTheme == ThemeHelper.CARD_DEEP_ORANGE);
        mCards[16].setSelected(currentTheme == ThemeHelper.CARD_BROWN);
        mCards[17].setSelected(currentTheme == ThemeHelper.CARD_GREY);
        mCards[18].setSelected(currentTheme == ThemeHelper.CARD_BLUE_GREY);


        if(ThemeHelper.getTheme(this)!=currentTheme){
            ThemeHelper.setTheme(this,currentTheme);
        }
    }



    public void changeToolbarAndNightModeAreaColor(int currentTheme){
        int color = ThemeHelper.getPrimaryColorByThemeId(this,currentTheme);
        toolbar.setBackgroundColor(color);
        int primaryColorLight = ThemeHelper.getPrimaryColorLightByThemeId(ColorPickerActivity.this,
                currentTheme);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(color);
        }
        nightModeArea.setBackgroundColor(primaryColorLight);
    }

}
