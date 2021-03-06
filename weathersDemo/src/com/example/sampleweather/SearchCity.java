package com.example.sampleweather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sampleweather.Application.EventHandler;
import com.example.sampleweather.adapter.ListViewAdapter;
import com.example.sampleweather.adapter.SearchCityAdapter;
import com.example.sampleweather.bean.City;
import com.example.sampleweather.ui.Slider;
import com.example.sampleweather.ui.Slider.OnTouchingLetterChangedListener;
import com.example.sampleweather.utils.CopyDataBase;

import za.co.immedia.pinnedheaderlistview.*;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView.OnItemClickListener;
import android.R.integer;
import android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchCity extends Activity implements EventHandler, TextWatcher {

	private static final String FORMAT = "^[a-z,A-Z].*$";
	private Slider slider;
	private TextView dialog;
	private PinnedHeaderListView listView;
	private Context mContext;
	private List<City> mCitys;
	private HashMap<String, List<City>> mMap;// 按首字母存数据
	private ArrayList<String> mSections;
	private List<Integer> mPositions;
	private Map<String, Integer> mIndexer;
	private ListViewAdapter adapter;
	private CopyDataBase mDataBase;
	private Application mApplication;
	private ListView mSearchListView;
	private SearchCityAdapter mSearchAdapter;
	private View mCityContainer;
	private View mSearchContainer;
	private EditText mEditText;
	private String[] strings;
	private List<String> city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_citychooes);
		mContext = this;
		// 此Activity接口添加
		Application.mListeners.add(this);
		initdata();
		initview();
	}

	private void initdata() {
		mApplication = Application.getInstance();

		// 判断城市数据加载是否完成
		System.out.println(mApplication.isCityCopyfinished());
		if (mApplication.isCityCopyfinished()) {
			// 获取城市列表数据
			mCitys = mApplication.getmCityList();
			// 获取头每个拼音的头位置数据
			mSections = mApplication.getmSections();
			// 获取首字母和城市数据的Map数据
			mMap = mApplication.getmMap();
			// System.out.println(mMap.toString());
			adapter = new ListViewAdapter(mContext, mMap, mSections, mCitys);
		}
	}

	private void initview() {
		// TODO Auto-generated method stub
		mEditText = (EditText) findViewById(R.id.input_search_query);
		mCityContainer = findViewById(R.id.city_content_container);
		mSearchContainer = findViewById(R.id.search_content_container);
		dialog = (TextView) findViewById(R.id.dialog);
		slider = (Slider) findViewById(R.id.slider);
		slider.setTextView(dialog);
		mEditText.addTextChangedListener(this);
		// 快速选择栏点击事件响应
		slider.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			@Override
			public void onTouchingLetterChanged(String s) {
				// TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(), s, 0).show();
				// 获取点击字母的在城市城市列表中的位置，因为Section也占位了，所以需要将Section的个数也加上
				int position = adapter.getPositionForSection(s.charAt(0))
						+ mSections.indexOf(s);
				System.out.println(s.charAt(0));
				if (position != -1) {
					listView.setSelection(position);
				}

			}
		});

		listView = (PinnedHeaderListView) findViewById(R.id.pinnedListView);
		listView.setAdapter(adapter);
		// listView点击事件，点击添加城市
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onSectionClick(AdapterView<?> adapterView, View view,
					int section, long id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int section, int position, long id) {
				// TODO Auto-generated method stub
				addCity(mMap.get(mSections.get(section)).get(position)
						.getCity());
				Toast.makeText(
						mContext,
						mMap.get(mSections.get(section)).get(position)
								.getCity(), 0).show();
				finish();
			}

		});
		// 搜索列表
		mSearchListView = (ListView) findViewById(R.id.searchcitylist);
		// 点击事件
		mSearchListView
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						addCity(mSearchAdapter.getItem(position).getCity());
						Toast.makeText(mContext,
								mSearchAdapter.getItem(position).getCity(), 0)
								.show();
						finish();

					}
				});
	}

//	重写接口的onCityComplite方法
	@Override
	public void onCityComplite() {
		// TODO Auto-generated method stub

		mCitys = mApplication.getmCityList();
		mSections = mApplication.getmSections();
		mMap = mApplication.getmMap();

		adapter = new ListViewAdapter(mContext, mMap, mSections, mCitys);
		listView.setAdapter(adapter);
		System.out.println(mApplication.isCityCopyfinished());
	}
//搜索文本框输入
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		mSearchAdapter = new SearchCityAdapter(mContext, mCitys);
		mSearchListView.setAdapter(mSearchAdapter);
		mSearchListView.setVisibility(View.VISIBLE);
		mSearchListView.setTextFilterEnabled(true);
		// System.out.println(s);
//		当城市列表 数据为空或者输入为空时，将搜索布局隐藏
		if (mCitys.size() < 1 || TextUtils.isEmpty(s)) {
			mCityContainer.setVisibility(View.VISIBLE);
			mSearchContainer.setVisibility(View.INVISIBLE);
			
		} else {
			mCityContainer.setVisibility(View.INVISIBLE);
			mSearchContainer.setVisibility(View.VISIBLE);
//			将输入文字进行筛选
			mSearchAdapter.getFilter().filter(s);

		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}
//添加城市
	private void addCity(String string) {

		city = new ArrayList<String>();
		strings = mApplication.getCity();
		if (strings.length != 0) {

			for (int i = 0; i < strings.length; i++) {

				if ((strings[i] != "")) {

					city.add(strings[i]);
				}
			}
		}
//过滤掉重复的，并写入
		if (city.indexOf(string) == -1) {
			city.add(string);
			strings = (String[]) city.toArray(new String[city.size()]);
			mApplication.getPreferenceUtils().setStrings(mContext, "city",
					strings);
			mApplication.setCity(strings);
		}

	}

}
