package com.szsszwl.materail_demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.magicasakura.widgets.TintButton;

import com.szsszwl.frameworkproj.download.NotificationDownloader;
import com.szsszwl.frameworkproj.download.DownloadService;
import com.szsszwl.frameworkproj.download.FileUtil;
import com.szsszwl.frameworkproj.download.MyDownloadListener;
import com.szsszwl.frameworkproj.download.callback.DownloadManager;
import com.szsszwl.frameworkproj.download.domain.DownloadInfo;
import com.szsszwl.frameworkproj.common_page.BaseActivityFrameworkLibrary;
import com.szsszwl.frameworkproj.util.download.DownloadCaller;

import java.io.File;
import java.lang.ref.SoftReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.szsszwl.frameworkproj.download.domain.DownloadInfo.STATUS_COMPLETED;
import static com.szsszwl.frameworkproj.download.domain.DownloadInfo.STATUS_REMOVED;
import static com.szsszwl.frameworkproj.download.domain.DownloadInfo.STATUS_WAIT;

/**
 * Created by DeskTop29 on 2018/10/30.
 */

public class DownloadDemoActivity extends BaseActivity {

    @BindView(R.id.url_input)
    EditText urlInput;

    @BindView(R.id.action_btn)
    TintButton actionBtn;

    @BindView(R.id.download_progress)
    ProgressBar downloadProgress;

    @BindView(R.id.download_status)
    TextView tv_status;

    @BindView(R.id.file_size)
    TextView tv_size;

    @BindView(R.id.speed)
    TextView speedText;

    @BindView(R.id.file_location)
    TextView fileLocation;

    @BindView(R.id.open_file)
    Button openFile;

    @BindView(R.id.notif1)
    Button notify1;

    @BindView(R.id.notif2)
    Button notify2;

    DownloadInfo downloadInfo = null;
    private DownloadManager downloadManager;
    long downloadedSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_demo_activity);
        ButterKnife.bind(this);
        setupToolbar();

        downloadManager = DownloadService.getDownloadManager(getApplicationContext());
        urlInput.setText("https://qd.myapp.com/myapp/qqteam/pcqq/QQ9.0.7.exe");

        initPermission();
    }


    @OnClick({R.id.action_btn, R.id.open_file,R.id.notif1,R.id.notif2})
    public void onClick(View v) {
        if (v.getId() == R.id.open_file) {
            String filePath = fileLocation.getText().toString();
            if (TextUtils.isEmpty(filePath)) {
                return;
            }
            File fileChild = new File(filePath);
            File parentFile = fileChild.getParentFile();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setDataAndType(Uri.fromFile(parentFile), "*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivity(intent);
        }
        else if(v.getId() == R.id.notif1){
            NotificationDownloader.getInstance(this)
                    .download("https://qd.myapp.com/myapp/qqteam/pcqq/QQ9.0.7.exe");
        }
        else if(v.getId() == R.id.notif2){
            String url = "http://wap.apk.anzhi.com/data4/apk/201810/30/9443330b2f4072ed5a5e1c3f3810bd8e_12325700.apk";
            /**
            NotificationDownloader.getInstance(this)
                    .download();
        */
            DownloadCaller.get().download(this,url);

        }
        else if (v.getId() == R.id.action_btn) {
            String url = urlInput.getText().toString();
            if (TextUtils.isEmpty(url)) {
                return;
            }

            if (downloadInfo != null) {
                switch (downloadInfo.getStatus()) {
                    case DownloadInfo.STATUS_NONE:
                    case DownloadInfo.STATUS_PAUSED:
                    case DownloadInfo.STATUS_ERROR:
                        //resume downloadInfo
                        downloadManager.resume(downloadInfo);
                        break;

                    case DownloadInfo.STATUS_DOWNLOADING:
                    case DownloadInfo.STATUS_PREPARE_DOWNLOAD:
                    case STATUS_WAIT:
                        //pause downloadInfo
                        downloadManager.pause(downloadInfo);
                        break;
                    case STATUS_COMPLETED:
                        downloadManager.remove(downloadInfo);
                        break;
                    case STATUS_REMOVED:
                        downloadInfo = null;
                        break;
                }
            } else {
                File d = new File("/sdcard/Downloads");
                if (!d.exists()) {
                    d.mkdirs();
                }
                String fileName = url.substring(url.lastIndexOf("/"));
                String path = d.getAbsolutePath().concat("/").concat(fileName);
                downloadInfo = new DownloadInfo.Builder().setUrl(url)
                        .setPath(path)
                        .build();
                downloadInfo
                        .setDownloadListener(new MyDownloadListener(new SoftReference(null)) {
                            @Override
                            public void onRefresh(DownloadInfo downloadInfo) {
                                notifyDownloadStatus();
                                refresh();
                            }
                        });
                downloadManager.download(downloadInfo);
                Log.i("download", "开启下载了");
            }
        }
    }


    private void notifyDownloadStatus() {

    }


    //动态申请储存卡写入权限
    private void initPermission() {
        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x24);
            return;
        }
    }


    //权限申请回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0x24:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                } else {
                    // 被禁止授权
                    Toast.makeText(this, "请至权限中心打开本应用的SD卡访问权限",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }



    private void refresh() {
        Log.i("download", "状态更新监听");

        if (downloadInfo == null) {
            tv_size.setText("");
            downloadProgress.setProgress(0);
            actionBtn.setText("下载");
            tv_status.setText("没有下载信息");
            speedText.setText("");
        } else {
            switch (downloadInfo.getStatus()) {
                case DownloadInfo.STATUS_NONE:
                    actionBtn.setText("下载");
                    tv_status.setText("没有下载信息");
                    downloadedSize = 0;
                    speedText.setText("");
                    break;
                case DownloadInfo.STATUS_PAUSED:
                case DownloadInfo.STATUS_ERROR:
                    actionBtn.setText("继续");
                    tv_status.setText("暂停");
                    try {
                        downloadProgress.setProgress((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tv_size.setText(FileUtil.formatFileSize(downloadInfo.getProgress()) + "/" + FileUtil
                            .formatFileSize(downloadInfo.getSize()));
                    downloadedSize = downloadInfo.getProgress();
                    speedText.setText("0kb/s");
                    break;

                case DownloadInfo.STATUS_DOWNLOADING:
                case DownloadInfo.STATUS_PREPARE_DOWNLOAD:
                    actionBtn.setText("暂停");
                    try {
                        downloadProgress.setProgress((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tv_size.setText(FileUtil.formatFileSize(downloadInfo.getProgress()) + "/" + FileUtil
                            .formatFileSize(downloadInfo.getSize()));
                    tv_status.setText("下载中...");
                    float speed = (downloadInfo.getProgress() - downloadedSize) / 1024;
                    speedText.setText(speed + "kb/s");
                    downloadedSize = downloadInfo.getProgress();
                    break;
                case STATUS_COMPLETED:
                    actionBtn.setText("删除");
                    try {
                        downloadProgress.setProgress((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tv_size.setText(FileUtil.formatFileSize(downloadInfo.getProgress()) + "/" + FileUtil
                            .formatFileSize(downloadInfo.getSize()));
                    tv_status.setText("下载成功");
                    downloadedSize = 0;
                    speedText.setText("0kb/s");
                    fileLocation.setText(downloadInfo.getPath());
                    break;
                case STATUS_REMOVED:
                    tv_size.setText("");
                    downloadProgress.setProgress(0);
                    actionBtn.setText("下载");
                    tv_status.setText("没有下载信息");
                    downloadedSize = 0;
                    speedText.setText("");
                    fileLocation.setText("");
                case STATUS_WAIT:
                    tv_size.setText("");
                    downloadProgress.setProgress(0);
                    actionBtn.setText("开始下载");
                    tv_status.setText("等待中...");
                    speedText.setText("");
                    break;
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
