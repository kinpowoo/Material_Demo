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
import android.support.v7.widget.Toolbar;
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
import com.szsszwl.materail_demo.sakura.BaseActivity;
import com.szsszwl.materail_demo.sakura.ColorPickerActivity;
import com.szsszwl.materail_demo.sakura.ThemeHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fab)
    TintFloatingActionButton fab;

    @BindView(R.id.content_text)
    TintTextView contentText;

    @BindView(R.id.tib)
    TintImageButton tintButton;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar = setupToolbar(false);


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
            startActivityForResult(new Intent(this, ColorPickerActivity.class),0x22);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED && requestCode == 0x22){
            //int themeId = data.getIntExtra("current_theme", ThemeHelper.CARD_PINK);
            ThemeHelper.refreshTheme(this);
        }
    }






}
