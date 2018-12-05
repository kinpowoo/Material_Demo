package com.szsszwl.materail_demo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.widgets.TintButton;
import com.szsszwl.frameworkproj.common_page.BaseApplicationFrameworkLibrary;
import com.szsszwl.frameworkproj.common_page.CommonAboutActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.common_page.CommonWebActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.common_page.camera_barcode.QRCodeScanActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.download.NotificationDownloader;
import com.szsszwl.frameworkproj.sakura_theme.ColorPickerActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.sakura_theme.ThemeHelper;
import com.szsszwl.frameworkproj.snackbar.MySnackbar;
import com.szsszwl.frameworkproj.util.ACache;
import com.szsszwl.frameworkproj.util.DataBus;
import com.szsszwl.frameworkproj.util.NetUtil;
import com.szsszwl.frameworkproj.util.PhoneInfo;
import com.szsszwl.frameworkproj.util.RxBus;
import com.szsszwl.frameworkproj.util.crypto.HttpRequest;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

import com.szsszwl.frameworkproj.custom_view.fab.FloatingActionMenu;
import com.szsszwl.frameworkproj.custom_view.fab.FloatingActionButton;

/**
 *    int currentPrimaryColor = ThemeUtils.getColorById(context, R.color.theme_color_primary);
 *                         int currentAccentColor = ThemeUtils.getColorById(context, R.color.theme_color_accent);
 *                         switch (view.getId()){
 *                             case R.id.fab_menu:
 *                                 FloatingActionMenu fabMenu = (FloatingActionMenu) view;
 *                                 fabMenu.setMenuButtonColorNormal(currentPrimaryColor);
 *                                 break;
 *                             case R.id.add_tab:
 *                                 FloatingActionButton fabAdd = (FloatingActionButton) view;
 *                                 fabAdd.setColorNormal(currentAccentColor);
 *                                 break;
 *                             case R.id.remove_tab:
 *                                 FloatingActionButton fabRemove = (FloatingActionButton) view;
 *                                 fabRemove.setColorNormal(currentAccentColor);
 *                                 break;
 *                         }
 */
public class DemoHubActivity extends BaseActivity {

    int[] testColors = {0xFF455A64, 0xFF00796B, 0xFF795548, 0xFF5B4947, 0xFFF57C00};
    int[] drawableIcon = {R.drawable.home, R.drawable.discover, R.drawable.alert, R.drawable.user,
            R.drawable.search};
    String[] bottomTitle = {"首页", "发现", "消息", "我的", "搜索"};

    @BindView(R.id.post_message)
    TintButton postMessage;
    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.device_info)
    EditText deviceInfo;

    @BindView(R.id.bottom_bar)
    PageNavigationView bottomBar;

    @BindView(R.id.fab_menu)
    FloatingActionMenu fabMenu;
    @BindView(R.id.add_tab)
    FloatingActionButton addTab;
    @BindView(R.id.remove_tab)
    FloatingActionButton removeTab;

    NavigationController navigationController;

    boolean fabOpened = false;

    Observable<String> observable;

    int mCurrentTheme;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar(false);


        mCurrentTheme = ThemeHelper.getTheme(this);
        int currentPrimaryColor = ThemeUtils.getColorById(this, R.color.theme_color_primary);
        int currentAccentColor = ThemeUtils.getColorById(this, R.color.theme_color_accent);
        fabMenu.setMenuButtonColorNormal(currentPrimaryColor);
        addTab.setColorNormal(currentAccentColor);
        removeTab.setColorNormal(currentAccentColor);


        /**
         * 判断是第一次进入应用
         */
        boolean isAlreadyStart = ACache.init().readBool("alreadyStart");
        if (!isAlreadyStart) {
            startActivity(new Intent(this,IntroActivity.class));
        }


        /**
         * 检查更新
         */
        HttpRequest.init().checkVersion("http://nod.ee56.com:12345/MobileAppService/CheckUpdate",
                new HttpRequest.CheckListener() {
                    @Override
                    public void checked(boolean needUpdate, boolean forceUpdate, final float version, final String updateUrl) {
                        Log.i("update", "is need update : " + needUpdate + "; is Force update :" + forceUpdate);
                        if (needUpdate) {
                            if (forceUpdate) {
                                Log.i("update", "is force update");
                                NotificationDownloader.getInstance(BaseApplicationFrameworkLibrary.getApp()).download(updateUrl);
                            } else {
                                Log.i("update", "isn't force update,but need update");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        HttpRequest.init().updateAlert(DemoHubActivity.this, version,
                                                new HttpRequest.ConfirmCallback() {
                                                    @Override
                                                    public void onConfirm() {
                                                        NotificationDownloader.getInstance(DemoHubActivity.this).download(updateUrl);
                                                    }
                                                });
                                    }
                                });
                            }
                        }
                    }
                });


        /**
         * 消息事件监听
         */
        observable = RxBus.get().register("message1", String.class);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        content.setText(content.getText().toString() + "\n" + o);
                    }
                });


        //Material风格
        navigationController = bottomBar.material()
                .addItem(R.drawable.home, "首页", testColors[0])
                .addItem(R.drawable.discover, "发现", testColors[1])
                .addItem(R.drawable.alert, "消息", testColors[2])
                .addItem(R.drawable.user, "我的", testColors[3])
                .setDefaultColor(0x89FFFFFF)  //未选中状态的颜色
                .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR)
                //这里可以设置样式模式，总共可以组合出4种效果
                .build();

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                goToPage(index);
            }

            @Override
            public void onRepeat(int index) {
                goToPage(index);
            }
        });

    }


    public void goToPage(int index) {
        if (index == 0) {
            go(CommonFuncDemoActivity.class);
        }
        if (index == 1) {
            go( CacheDemoActivity.class);
        }
        if (index == 2) {
            go(EventBusDemoActivity.class);
        }
        if (index == 3) {
            go(CommonItemDemoActivity.class);
        }
        if (index == 4) {
            go(CommonMessageDemoActivity.class);
        }
    }


    @OnClick({R.id.add_tab, R.id.remove_tab, R.id.post_message, R.id.enter_web,
            R.id.open_wifi, R.id.close_wifi, R.id.open_mobile_network, R.id.close_mobile_network,
            R.id.get_device_info, R.id.enter_about, R.id.enter_qrcode_scan, R.id.load_apk,
            R.id.show_dialog})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_tab:
                addTab();
                break;
            case R.id.remove_tab:
                removeTab();
                break;
            case R.id.post_message:
                DataBus.get().post("message2", "我是从MainActivity发出的消息");
                break;
            case R.id.enter_web:
                go(CommonWebActivityFrameworkLibrary.class);
                break;
            case R.id.open_wifi:
                NetUtil.setWifiEnabled(this, true);
                break;
            case R.id.close_wifi:
                NetUtil.setWifiEnabled(this, false);
                break;
            case R.id.open_mobile_network:
                NetUtil.setMobileDataEnabled(this, true);
                break;
            case R.id.close_mobile_network:
                NetUtil.setMobileDataEnabled(this, false);
                break;
            case R.id.get_device_info:
                deviceInfo.setText(PhoneInfo.getDeviceInfo());
                break;
            case R.id.enter_about:
                break;
            case R.id.enter_qrcode_scan:
                go(QRCodeScanActivityFrameworkLibrary.class);
                //startActivity(new Intent(this,MipCaptureActivity.class));
                break;
            case R.id.load_apk:
                //安装、更新插件,使用如下方法：
                /**
                String apkPath = "/sdcard/Downloads/rx.apk";
                PackageManager pm = getPackageManager();
                PackageInfo info = pm.getPackageArchiveInfo(apkPath, 0);
                if (!PluginManager.getInstance().isConnected()) {
                    Toast.makeText(this, "插件服务正在初始化，请稍后再试。。。", Toast.LENGTH_SHORT).show();
                }
                try {
                    if (PluginManager.getInstance().getPackageInfo(info.packageName, 0) != null) {
                        Toast.makeText(this, "已经安装了，不能再安装", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "开始安装插件。。。", Toast.LENGTH_SHORT).show();
                        PluginManager.getInstance().installPackage(apkPath, 0);
                    }
                } catch (RemoteException ex) {
                    Toast.makeText(this, "插件安装失败", Toast.LENGTH_SHORT).show();
                    Log.e("apkLoad", "安装插件失败" + ex.getMessage());
                } finally {
                    Intent intent = pm.getLaunchIntentForPackage(info.packageName);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

*/
                /**
                 * 说明：安装插件到插件系统中，filepath为插件apk路径，flags可以设置为0，
                 * 如果要更新插件，则设置为PackageManagerCompat.INSTALL_REPLACE_EXISTING
                 * 返回值及其含义请参见PackageManagerCompat类中的相关字段。
                 */

                //卸载插件，使用如下方法：
                /**
                 * 说明：从插件系统中卸载某个插件，packageName传插件包名即可，flags传0。
                 */
                //int PluginManager.getInstance().deletePackage(String packageName,int flags);


                /**
                 * 启动插件：启动插件的Activity、Service等都和你启动一个以及安装在系统中的app一样，
                 * 使用系统提供的相关API即可。组件间通讯也是如此。
                 */
                break;
            case R.id.show_dialog:
                if(bottomSheetDialog == null){
                    bottomSheetDialog = new BottomSheetDialog(this);
                    View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,null);
                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.setCancelable(true);
                    bottomSheetDialog.setCanceledOnTouchOutside(true);
                    // 解决下滑隐藏dialog 后，再次调用show 方法显示时，不能弹出Dialog
                    View view1 = bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
                    final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view1);
                    bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                        @Override
                        public void onStateChanged(@NonNull View bottomSheet, int newState) {
                            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                                Log.i("BottomSheet","onStateChanged");
                                bottomSheetDialog.dismiss();
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            }
                        }
                        @Override
                        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        }
                    });
                    bottomSheetDialog.show();
                }else{
                    bottomSheetDialog.show();
                }

                break;
        }
    }

    public void addTab() {
        if (navigationController.getItemCount() < 5) {
            int index = new Random().nextInt(4);
            Drawable drawable = ContextCompat.getDrawable(this, drawableIcon[index]);
            navigationController.addMaterialItem(index, drawable, drawable, bottomTitle[index],
                    testColors[index]);
        } else {
            Log.i("tab", "执行了snack提示操作");
            MySnackbar.make(fabMenu, "材料设计模式下，导航栏数量不要超过5个",
                    2000, -1).show();

            Snackbar.make(fabMenu,"材料设计模式下，导航栏数量不要超过5个",
                    Snackbar.LENGTH_SHORT).show();
        }
    }


    public void removeTab() {
        int index = new Random().nextInt(3);
        if (navigationController.getItemCount() > 3) {
            navigationController.removeItem(index);
        } else {
            MySnackbar.make(fabMenu, "移除失败(不能少于3个)",
                    2000, -1).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_color_platte) {
            goWithResult(ColorPickerActivityFrameworkLibrary.class,0x22);
            return true;
        } else if (id == R.id.nav_remote_download) {
            go(DownloadDemoActivity.class);
            return true;
        } else if (id == R.id.nav_web) {
            go(WebDemoActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 0x22) {
            int theme = data.getIntExtra("theme", -1);
            if (theme != -1 && theme != mCurrentTheme) {
                mCurrentTheme = theme;
                ThemeHelper.refreshThemeInstant(this);
            }
        }
    }


    //fab动画
    public void openMenu(final View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0, -155, -135);
        animator.setDuration(500);
        animator.start();
        backgroundAlpha(0.9f);

        addTab.setVisibility(View.VISIBLE);
        removeTab.setVisibility(View.VISIBLE);
        ValueAnimator scaleToShow = ValueAnimator.ofFloat(0, 1f);
        scaleToShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                addTab.setScaleX(val);
                addTab.setScaleY(val);
                removeTab.setScaleX(val);
                removeTab.setScaleY(val);
            }
        });
        scaleToShow.setDuration(500);
        scaleToShow.start();
        fabOpened = true;
    }

    public void closeMenu(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", -135, 20, 0);
        animator.setDuration(500);
        animator.start();
        backgroundAlpha(1);

        ValueAnimator scaleToShow = ValueAnimator.ofFloat(1, 0f);
        scaleToShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                addTab.setScaleX(val);
                addTab.setScaleY(val);
                removeTab.setScaleX(val);
                removeTab.setScaleY(val);

                if (val == 0) {
                    addTab.setVisibility(View.GONE);
                    removeTab.setVisibility(View.GONE);
                }
            }
        });
        scaleToShow.setDuration(700);
        scaleToShow.start();
        fabOpened = false;
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = DemoHubActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        DemoHubActivity.this.getWindow().setAttributes(lp);
        DemoHubActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister("message1", observable);
    }
}
