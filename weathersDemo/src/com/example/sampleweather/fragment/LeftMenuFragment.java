package com.example.sampleweather.fragment;

import com.example.sampleweather.Application;
import com.example.sampleweather.CityManergerActivity;
import com.example.sampleweather.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LeftMenuFragment extends BaseFragment implements OnClickListener {


	private Button mCityManerger;
	private Button mAbout;
	private Button mSetting;
	private Application mApplication ; 
	private SlidingMenu slidingMenu;

	@Override
	public View initview() {
		// TODO Auto-generated method stub
		View view = View.inflate(mActivity, R.layout.fragmentleftenu_activity, null);
		mCityManerger = (Button) view.findViewById(R.id.menubutton);
		mAbout = (Button) view.findViewById(R.id.menubutton1);
		mSetting =(Button) view.findViewById(R.id.menubutton2);
		return view;
	}

	@Override
	public void initdata() {
		// TODO Auto-generated method stub
	  mApplication = Application.getInstance();
	  slidingMenu = mApplication.getSlidingMenu();
      mCityManerger.setOnClickListener(this);
      mAbout.setOnClickListener(this);
      mSetting.setOnClickListener(this);
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

		default:
			break;
		}
	}

	
}
