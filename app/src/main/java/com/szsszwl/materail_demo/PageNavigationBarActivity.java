package com.szsszwl.materail_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.szsszwl.frameworkproj.bottom_tablayout.OnlyIconItemView;
import com.szsszwl.frameworkproj.bottom_tablayout.SpecialTab;
import com.szsszwl.frameworkproj.bottom_tablayout.SpecialTabRound;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import me.majiajie.pagerbottomtabstrip.listener.SimpleTabItemSelectedListener;

/**
 * Created by DeskTop29 on 2018/10/15.
 */

public class PageNavigationBarActivity extends AppCompatActivity {

    int[] testColors = {0xFF455A64, 0xFF00796B, 0xFF795548, 0xFF5B4947, 0xFFF57C00};

    @BindView(R.id.nav_toolbar)
    TintToolbar toolbar;

    @BindView(R.id.bottom_bar)
    PageNavigationView pageNavigationView;

    @BindView(R.id.bottom_bar2)
    PageNavigationView pageNavigationView2;

    @BindView(R.id.bottom_bar3)
    PageNavigationView pageNavigationView3;

    @BindView(R.id.bottom_bar4)
    PageNavigationView pageNavigationView4;

    @BindView(R.id.bottom_bar5)
    PageNavigationView pageNavigationView5;


    @BindView(R.id.bottom_bar6)
    PageNavigationView pageNavigationView6;


    NavigationController controller4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_button_demo_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        //Material风格
        NavigationController controller1 = pageNavigationView.material()
                .addItem(R.drawable.ic_menu_camera,"Camera",testColors[0])
                .addItem(R.drawable.ic_menu_gallery,"Gallery",testColors[1])
                .addItem(R.drawable.ic_menu_manage,"Manage",testColors[2])
                .addItem(R.drawable.ic_menu_send,"Send",testColors[3])
                .setDefaultColor(0x89FFFFFF)  //未选中状态的颜色
                .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR | MaterialMode.HIDE_TEXT)//这里可以设置样式模式，总共可以组合出4种效果
                .build();

        //自定义的风格
        NavigationController controller2 = pageNavigationView2.custom()
                .addItem(newNormalItem(R.drawable.ic_menu_camera,R.drawable.ic_menu_camera,"Camera"))
                .addItem(newNormalItem(R.drawable.ic_menu_gallery,R.drawable.ic_menu_gallery,"Gallery"))
                .addItem(newNormalItem(R.drawable.ic_menu_manage,R.drawable.ic_menu_manage,"Manage"))
                .addItem(newNormalItem(R.drawable.ic_menu_send,R.drawable.ic_menu_send,"Send"))
                .build();
        //设置消息数,第一个参数是下标,第二个参数是数量
        controller2.setMessageNumber(1, 8);
        //设置显示小圆点,设置只有小红点,没有数理
        controller2.setHasMessage(0, true);


        //自定义风格2,只有图标没有文字
        NavigationController controller3 = pageNavigationView3.custom()
                .addItem(newOnlyOneItem(R.drawable.ic_menu_camera,R.drawable.ic_menu_camera))
                .addItem(newOnlyOneItem(R.drawable.ic_menu_gallery,R.drawable.ic_menu_gallery))
                .addItem(newOnlyOneItem(R.drawable.ic_menu_manage,R.drawable.ic_menu_manage))
                .addItem(newOnlyOneItem(R.drawable.ic_menu_send,R.drawable.ic_menu_send))
                .build();


        //material风格,配合CoordinateLayout会在屏幕滚动时消失,滚动停止又出现
        controller4 = pageNavigationView4.material()
                .addItem(R.drawable.ic_menu_camera,"Camera")
                .addItem(R.drawable.ic_menu_gallery,"Gallery")
                .addItem(R.drawable.ic_menu_manage,"Manage")
                .addItem(R.drawable.ic_menu_send,"Send")
                .build();


        //创建一个有圆形tab的 Item
        NavigationController controller5 = pageNavigationView5.custom()
                .addItem(newSpecialItem(R.drawable.ic_menu_camera,R.drawable.ic_menu_camera,"Camera"))
                .addItem(newSpecialItem(R.drawable.ic_menu_gallery,R.drawable.ic_menu_gallery,"Gallery"))
                .addItem(newRoundItem(R.drawable.ic_menu_slideshow,R.drawable.ic_menu_slideshow,"SlideShow"))
                .addItem(newSpecialItem(R.drawable.ic_menu_manage,R.drawable.ic_menu_manage,"Manage"))
                .addItem(newSpecialItem(R.drawable.ic_menu_send,R.drawable.ic_menu_send,"Send"))
                .build();


        NavigationController controller6 = pageNavigationView6.material()
                .addItem(R.drawable.ic_menu_camera,"Camera",testColors[0])
                .addItem(R.drawable.ic_menu_gallery,"Gallery",testColors[1])
                .addItem(R.drawable.ic_menu_manage,"Manage",testColors[2])
                .addItem(R.drawable.ic_menu_send,"Send",testColors[3])
                .enableVerticalLayout()//使用垂直布局
                .build();

        //条目被选择简单回调
        controller1.addSimpleTabItemSelectedListener(new SimpleTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                // 选中时触发
            }
        });

        //条目被选择完整回调
        controller2.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int i, int i1) {

            }

            @Override
            public void onRepeat(int i) {

            }
        });
    }


    //通过监听列表滑动来隐藏和显示底部导航栏
    private class ListScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 8) {//列表向上滑动
                controller4.hideBottomLayout();
            } else if (dy < -8) {//列表向下滑动
                controller4.showBottomLayout();
            }
        }
    }



    //创建一个Normal风格的Item
    private BaseTabItem newNormalItem(int drawable, int checkedDrawable, String text){
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable,checkedDrawable,text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(0xFF009688);
        return normalItemView;
    }


    //创建一个OnlyIcon只有图标风格的Item
    private BaseTabItem newOnlyOneItem(int drawable, int checkedDrawable){
        OnlyIconItemView onlyIconItemView = new OnlyIconItemView(this);
        onlyIconItemView.initialize(drawable, checkedDrawable);
        return onlyIconItemView;
    }


    /**
     * 正常tab
     */
    private BaseTabItem newSpecialItem(int drawable, int checkedDrawable, String text){
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFF009688);
        return mainTab;
    }

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable,int checkedDrawable,String text){
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFF009688);
        return mainTab;
    }
}
