package com.szsszwl.materail_demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.widgets.TintTextView;
import com.bilibili.magicasakura.widgets.TintToolbar;
import com.szsszwl.materail_demo.custom_tint_view.TintFloatingActionButton;
import com.szsszwl.materail_demo.custom_tint_view.TintImageButton;
import com.szsszwl.materail_demo.sakura.CardPickerDialog;
import com.szsszwl.materail_demo.sakura.ThemeHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        CardPickerDialog.ClickListener {

    @BindView(R.id.fab)
    TintFloatingActionButton fab;
    @BindView(R.id.toolbar)
    TintToolbar toolbar;

    @BindView(R.id.content_text)
    TintTextView contentText;

    @BindView(R.id.tib)
    TintImageButton tintButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //设actionBar为自定义的Toolbar
        setSupportActionBar(toolbar);
        Log.i("tag","Activity on Create");


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //抽屉中的菜单控件
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @OnClick(R.id.tib)
    public void click(){
        Snackbar.make(tintButton, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("tag","Activity on Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag","Activity on Stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("tag","Activity on Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag","Activity on Destroy");

    }

    public void nightShift(View v){
        Log.i("tag","night mode be click");


        if(Build.VERSION.SDK_INT <= 16){
            ThemeUtils.updateNightMode(getResources(),true);
        }else{
            int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (mode == Configuration.UI_MODE_NIGHT_YES) {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }


    }



    //加载菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    //ToolBar右侧的菜单选中监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //抽屉菜单选中监听
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_color_platte) {
            //调色板被点击
            CardPickerDialog dialog = new CardPickerDialog();
            dialog.setClickListener(this);
            dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
        } else if (id == R.id.nav_navigation) {
            startActivity(new Intent(this, PageNavigationBarActivity.class));
        } else if (id == R.id.collapse) {
            startActivity(new Intent(this, CollapsingLayoutActivity.class));
        } else if (id == R.id.nav_manage) {

        }else if (id == R.id.night_mode_switch){

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //调色板中颜色被点击回调
    @Override
    public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme(MainActivity.this) != currentTheme) {
            ThemeHelper.setTheme(MainActivity.this, currentTheme);
            ThemeUtils.refreshUI(MainActivity.this, new ThemeUtils.ExtraRefreshable() {
                        @Override
                        public void refreshGlobal(Activity activity) {
                            //for global setting, just do once
                            if (Build.VERSION.SDK_INT >= 21) {
                                final MainActivity context = MainActivity.this;
                                ActivityManager.TaskDescription taskDescription =
                                        new ActivityManager.TaskDescription(null, null,
                                                ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                setTaskDescription(taskDescription);
                                getWindow().setStatusBarColor(
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


    private ColorStateList createColorStateList(int normal,int pressed) {
        return createColorStateList(normal, pressed, 0xff0000ff, 0xffff0000);
    }

    //生成ColorStateList对象，实际上是一个selector的drawable对象
    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
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
    public ColorStateList getColorStateListForXML(int xmlSelectorID){
        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(xmlSelectorID);
        if(csl!=null){
            return csl;
        }
        return null;
    }

    public ColorStateList createColorStateListForXML(int xmlSelectorID) {
        XmlResourceParser xpp=Resources.getSystem().getXml(xmlSelectorID);
        try {
            ColorStateList csl= ColorStateList.createFromXml(getResources(),xpp);
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
