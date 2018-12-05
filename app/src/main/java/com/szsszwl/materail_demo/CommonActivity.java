package com.szsszwl.materail_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.szsszwl.frameworkproj.R;
import com.szsszwl.frameworkproj.webview.common.FragmentKeyDown;
import com.szsszwl.frameworkproj.webview.fragment.AgentWebFragment;
import com.szsszwl.frameworkproj.webview.fragment.CustomIndicatorFragment;
import com.szsszwl.frameworkproj.webview.fragment.JsAgentWebFragment;

/**
 * Created by cenxiaozhong on 2017/5/23.
 * source code  https://github.com/Justson/AgentWeb
 */

public class CommonActivity extends BaseActivity {

	public static final int USE_IN_FRAGMENT = 0x0;
	public static final int FILE_DOWNLOAD = 0x1;
	public static final int INPUT_TAG_PROBLEM = 0x2;
	public static final int JS_JAVA_COMUNICATION_UPLOAD_FILE = 0x3;
	public static final int JS_JAVA_COMMUNICATION = 0x4;
	public static final int VIDEO_FULL_SCREEN = 0x5;
	public static final int CUSTOM_PROGRESSBAR = 0x6;


	private FrameLayout mFrameLayout;
	public static final String TYPE_KEY = "type_key";
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common);

		mFrameLayout = findViewById(R.id.container_frame);
		int key = getIntent().getIntExtra(TYPE_KEY, -1);
		mFragmentManager = this.getSupportFragmentManager();
		openFragment(key);
	}


	private AgentWebFragment mAgentWebFragment;

	private void openFragment(int key) {

		FragmentTransaction ft = mFragmentManager.beginTransaction();
		Bundle mBundle = null;

		switch (key) {
            /*Fragment 使用AgenWeb*/
			case USE_IN_FRAGMENT: //项目中请使用常量代替0 ， 代码可读性更高
				ft.add(R.id.container_frame, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "https://m.vip.com/?source=www&jump_https=1");
				break;
			/*下载文件*/
			case FILE_DOWNLOAD:
				ft.add(R.id.container_frame, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "http://android.myapp.com/");
				break;
            /*input标签上传文件*/
			case INPUT_TAG_PROBLEM:
				ft.add(R.id.container_frame, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "file:///android_asset/upload_file/uploadfile.html");
				break;
            /*Js上传文件*/
			case JS_JAVA_COMUNICATION_UPLOAD_FILE:
				ft.add(R.id.container_frame, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "file:///android_asset/upload_file/jsuploadfile.html");
				break;
            /*Js与Java交互*/
			case JS_JAVA_COMMUNICATION:
				ft.add(R.id.container_frame, mAgentWebFragment = JsAgentWebFragment.getInstance(mBundle = new Bundle()), JsAgentWebFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "file:///android_asset/js_interaction/hello.html");
				break;

            /*优酷全屏播放视屏*/
			case VIDEO_FULL_SCREEN:
				ft.add(R.id.container_frame, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "http://m.youku.com/video/id_XODEzMjU1MTI4.html");
				break;
            /*淘宝自定义进度条*/
			case CUSTOM_PROGRESSBAR:
				ft.add(R.id.container_frame, mAgentWebFragment = CustomIndicatorFragment.getInstance(mBundle = new Bundle()), CustomIndicatorFragment.class.getName());
				mBundle.putString(AgentWebFragment.URL_KEY, "https://m.taobao.com/?sprefer=sypc00");
				break;
			default:
				break;

		}
		ft.commit();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//一定要保证 mAentWebFragemnt 回调
		mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		AgentWebFragment mAgentWebFragment = this.mAgentWebFragment;
		if (mAgentWebFragment != null) {
			FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
			if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event)) {
				return true;
			} else {
				return super.onKeyDown(keyCode, event);
			}
		}

		return super.onKeyDown(keyCode, event);
	}




	@Override
	protected void onDestroy() {
		super.onDestroy();

	}
}
