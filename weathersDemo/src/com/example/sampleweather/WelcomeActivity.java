package com.example.sampleweather;

import com.example.sampleweather.utils.PreferenceUtils;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class WelcomeActivity extends Activity {
	private static final int  GOTO_MAIN_ACTIVITY = 0 ;
    private Application mApplication ; 
    private String[] cityStrings;
    private Handler mHandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case GOTO_MAIN_ACTIVITY :
//				判断是否第一次进入，当城市列表为空时进入也会跳转到城市选择界面
				if (isfisrtcoming || cityStrings.length == 0) {
					Intent intents = new Intent(WelcomeActivity.this, CityManergerActivity.class);
					startActivity(intents);
//					将第一次进入置false
					PreferenceUtils.setBoolean(WelcomeActivity.this, "FIRST_COMING", false);
					finish();
				}else  {
					
					Intent intents = new Intent(WelcomeActivity.this, MainActivity.class);
					startActivity(intents);
					finish();
				}
				break;

			default:
				break;
			}
		};
		
	};
private boolean isfisrtcoming;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcomeactivity);
		mApplication = Application.getInstance();
//		获取城市列表
		 cityStrings  =  mApplication.getCity();
		isfisrtcoming    = PreferenceUtils.getBoolean(WelcomeActivity.this, "FIRST_COMING", true);
		Message msg = Message.obtain();
		msg.obj = GOTO_MAIN_ACTIVITY ;
		
		mHandler.sendMessageDelayed(msg, 1000);//延迟1秒跳转
	}
	
	
}
