package com.example.sampleweather.viewpage;

import java.util.ArrayList;

import com.example.sampleweather.R;
import com.example.sampleweather.bean.WeatherData;








import android.R.string;
import android.app.Activity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public abstract class BasePage {

	public Activity mActivity;
	public  View mRoot;
	public TextView mNowTemp;
	public GridView mGridView;
	public ImageView mNowIcon;
    public TextView mCurrentCIty;
	public TextView mTodayTemp;
	public ScrollView mScrollView;
	public ListView mListview;
	public TextView mTV;
	public TextView mAqi;
	public TextView mAqiInfo;
	public TextView mCw;
	public TextView mCwInfo;
	public TextView mDrsg;
	public TextView mDrsgInfo;
	public TextView mSport;
	public TextView mSportInfo;
	public TextView mTravl;
	public TextView mTravlInfo;
	public TextView mUv;
	public TextView mUvInfo;
	public TextView mFlu;
	public TextView mFluInfo;
	public ImageView mRefresh;
	public TextView mAqidate ;
	public TextView mTianqi;
	public TextView mFengli;
	public TextView mJiangshui;
	public TextView mFengxiang;
	public TextView mNengjiandu;
	public TextView mShidu;
	
	
	public  BasePage(Activity activity , String string , String city) {
		// TODO Auto-generated constructor stub
		mActivity = activity;
		mRoot = initview();	
	}

	public View initview() {
		// TODO Auto-generated method stub
     View view =  View.inflate(mActivity, R.layout.basepage, null);
     mNowIcon = (ImageView) view.findViewById(R.id.tempicon);
     mNowTemp =(TextView) view.findViewById(R.id.nowtemp);
     mTodayTemp =(TextView) view.findViewById(R.id.daytemp);
     mCurrentCIty = (TextView) view.findViewById(R.id.location);
//     mDayTemp = (TextView) view.findViewById(R.id.daytemp );
     mGridView = (GridView) view.findViewById(R.id.gridview);
//     mListview = (ListView) view.findViewById(R.id.listviewinfo);
//     mScrollView =(ScrollView) view.findViewById(R.id.scrollview);
     mRefresh = (ImageView ) view.findViewById(R.id.refresh);
     
     mAqi = (TextView) view.findViewById(R.id.aqi );
     mAqiInfo = (TextView) view.findViewById(R.id.aqiinfo );
     mCw = (TextView) view.findViewById(R.id.cw );
     mCwInfo = (TextView) view.findViewById(R.id.cwinfo );
     mDrsg = (TextView) view.findViewById(R.id.drsg );
     mDrsgInfo = (TextView) view.findViewById(R.id.drsginfo );
     mSport = (TextView) view.findViewById(R.id.sport );
     mSportInfo = (TextView) view.findViewById(R.id.sportinfo );
     mTravl = (TextView) view.findViewById(R.id.travl );
     mTravlInfo = (TextView) view.findViewById(R.id.travlinfo );
     mUv = (TextView) view.findViewById(R.id.uv );
     mUvInfo = (TextView) view.findViewById(R.id.uvinfo );
     mFlu = (TextView) view.findViewById(R.id.ganmao );
     mFluInfo = (TextView) view.findViewById(R.id.ganmaoinfo);
     mTianqi = (TextView) view.findViewById(R.id.tianqileixing);
     mJiangshui = (TextView) view.findViewById(R.id.jiangshui);
     mFengli = (TextView) view.findViewById(R.id.fengli);
     mFengxiang = (TextView) view.findViewById(R.id.fengxiang);
     mNengjiandu = (TextView) view.findViewById(R.id.nengjiandu);
     mShidu = (TextView) view.findViewById(R.id.shidu);
//     mAqidate = (TextView) view.findViewById(R.id.aqidate);
    
    
  
     return view;
	}
	
	
	public abstract void initdata();

}
