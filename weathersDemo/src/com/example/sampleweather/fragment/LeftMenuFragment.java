package com.example.sampleweather.fragment;

import com.example.sampleweather.Application;
import com.example.sampleweather.CityManergerActivity;
import com.example.sampleweather.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LeftMenuFragment extends BaseFragment implements OnClickListener {


	private Button mCityManerger;
	private Button mAbout;
	private Button mVersion;
	private Application mApplication ; 
	private SlidingMenu slidingMenu;

	@Override
	public View initview() {
		// TODO Auto-generated method stub
		View view = View.inflate(mActivity, R.layout.fragmentleftenu_activity, null);
		mCityManerger = (Button) view.findViewById(R.id.menubutton);
		mAbout = (Button) view.findViewById(R.id.menubutton1);
		mVersion =(Button) view.findViewById(R.id.menubutton2);
		return view;
	}

	@Override
	public void initdata() {
		// TODO Auto-generated method stub
	  mApplication = Application.getInstance();
	  slidingMenu = mApplication.getSlidingMenu();
      mCityManerger.setOnClickListener(this);
      mAbout.setOnClickListener(this);
      mVersion.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.menubutton:
			Intent intent = new Intent(mActivity , CityManergerActivity.class);
			startActivity(intent);
//			slidingMenu.toggle();
			break;
		case R.id.menubutton1: 
			new AlertDialog.Builder(mActivity)
            .setTitle("关于")
            .setMessage("这个APP是学习过程中写的第一个整体APP，有bug请见谅。")
            .setPositiveButton("确定",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
//						finish();
					}
				} ).show();
			break;
		case R.id.menubutton2: 
			new AlertDialog.Builder(mActivity)
            .setTitle("版本")
            .setMessage("V1.0")
            .setPositiveButton("确定",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
//						finish();
					}
				} ).show();
			break;
		default:
			break;
		}
	}

	
}
