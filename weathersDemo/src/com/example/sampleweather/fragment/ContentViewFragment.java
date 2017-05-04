package com.example.sampleweather.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.sampleweather.Application;
import com.example.sampleweather.R;
import com.example.sampleweather.CityManergerActivity.Adapter;
import com.example.sampleweather.bean.City;
import com.example.sampleweather.bean.GlobleData;
import com.example.sampleweather.bean.WeatherData;
import com.example.sampleweather.viewpage.BasePage;
import com.example.sampleweather.viewpage.WeatherView;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ContentViewFragment extends BaseFragment {
	private ArrayList<BasePage> mPagelist;
	private ViewPager mViewpage;
	private Application mApplication;
	private List<String> city;
	private String[] strings;
	private MyAdapter adapter;
	private List<City> mCityList;
	private HashMap<String, String> mCityID;
	private int page;

    public ContentViewFragment( int i) {
	// TODO Auto-generated constructor stub
    	page = i;
    	
    }
	@Override
	public View initview() {
		// TODO Auto-generated method stub
		View view = View.inflate(mActivity, R.layout.fragmentcontentview, null);
		mViewpage = (ViewPager) view.findViewById(R.id.viewpage);
		// data=new ArrayList<WeatherData>(null);
		return view;
	}

	@Override
	public void initdata() {
		mPagelist = new ArrayList<BasePage>();
		mApplication = Application.getInstance();
		city = new ArrayList<String>();
		mCityID = mApplication.getmCityID();
		adapter = new MyAdapter();
		 mCityList = mApplication.getmCityList();
		strings = mApplication.getCity();
		if (strings.length != 0) {

			for (int i = 0; i < strings.length; i++) {

				if ((strings[i] != "")) {

					city.add(strings[i]);
				}
			}
		}
		System.out.println("size"+city.size());
		for (String string : city) {
			String id = mCityID.get(string);
			mPagelist.add(new WeatherView(mActivity, id ,string));
//			mPagelist.add(new WeatherView(mActivity, "101301201"));
		}
		System.out.println("pagesize"+mPagelist.size());
		mViewpage.setAdapter(adapter);
		mViewpage.setCurrentItem(page);
	}

	
 	public void updataCity(){
 		city.removeAll(city);
 		mPagelist.removeAll(mPagelist);
 		strings = mApplication.getCity();
		if (strings.length != 0) {

			for (int i = 0; i < strings.length; i++) {

				if ((strings[i] != "")) {

					city.add(strings[i]);
				}
			}
		}
		
		for (String string : city) {
			String id = mCityID.get(string);
			mPagelist.add(new WeatherView(mActivity, id ,string));
//			mPagelist.add(new WeatherView(mActivity, "101301201"));
		}
 		adapter.notifyDataSetChanged();
 		
 		
 	}
	
	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPagelist.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			// BasePage page = mPagelist.get(position);
			BasePage page = mPagelist.get(position);
			View view = page.mRoot;
			page.initdata();
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
		
		
		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return PagerAdapter.POSITION_NONE;
		}

	}
	
    @Override
    public void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	updataCity();
    	
        
    }
//  

}
