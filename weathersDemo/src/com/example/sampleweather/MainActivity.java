package com.example.sampleweather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.sampleweather.bean.WeatherData;
import com.example.sampleweather.fragment.ContentViewFragment;
import com.example.sampleweather.fragment.LeftMenuFragment;
import com.example.sampleweather.utils.LogUtils;
import com.example.sampleweather.utils.PreferenceUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.loc.m;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends SlidingFragmentActivity {

	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_MAIN_VIEW = "TAG_MAIN_VIEW";

	private String[] result;
	private SlidingMenu slidingMenu;
	private Application mApplication;
	private int position = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 设置侧边栏
//获取侧边栏对象
		mApplication = Application.getInstance();
		setBehindContentView(R.layout.leftmenu);
		slidingMenu = getSlidingMenu();
		mApplication.setSlidingMenu(slidingMenu);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setBehindOffset(150);
		initFragment();
		// Intent intent = getIntent();
		// position = Integer.parseInt(intent.getStringExtra("page"));

	}

	private void initFragment() {
		// TODO Auto-generated method stub
//		获取fragmentManager 对象
		FragmentManager fragmentManager = getSupportFragmentManager();
//		开启fragment 事务
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
//替换布局
		fragmentTransaction.replace(R.id.activity_main,
				new ContentViewFragment(position), TAG_MAIN_VIEW);
		fragmentTransaction.replace(R.id.leftmenu, new LeftMenuFragment(),
				TAG_LEFT_MENU);
//提交事务
		fragmentTransaction.commit();
	}

	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
	}

	protected void onPause() {
		super.onPause();
		System.out.println("onPause");
	}

	protected void onRestart() {
		super.onRestart();
//		比较时间，是否需要重新加载数据
		mApplication.comparetime();
		int time = (int) mApplication.getMinute();
		System.out.println("OnRestart"+ time);
	}

	protected void onResume() {
		super.onResume();
		System.out.println("OnResume");
		Intent localIntent = getIntent();
		System.out.println("intent.getStringExtra "
				+ localIntent.getStringExtra("page"));
//		城市选择页面点击城市跳转携带第几页参数，initFragment
		if (localIntent.getStringExtra("page") != null) {
			this.position = Integer.valueOf(localIntent.getStringExtra("page"))
					.intValue();
			initFragment();
		}
	}

	protected void onStart() {
		super.onStart();
		System.out.println("onStart");
	}

	protected void onStop() {
		super.onStop();
//		写入Stop的时间
		long l = System.currentTimeMillis();
		String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(l));
		System.out.println(str);
		PreferenceUtils.setTime(this, "time", str);
		System.out.println("onStop");
	}

}
