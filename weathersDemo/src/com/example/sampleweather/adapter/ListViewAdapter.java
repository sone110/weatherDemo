package com.example.sampleweather.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sampleweather.R;
import com.example.sampleweather.bean.City;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

/**
 * @author Administrator
 *
 */
public class ListViewAdapter extends SectionedBaseAdapter implements SectionIndexer  {

	private Context mContext ;

	private Map<String, List<City>> map;

	private ArrayList<String> mSceton;

	private List<City> citys;
	public ListViewAdapter(Context context , HashMap<String, List<City>> map,ArrayList<String> mScetion,List<City> citys) {
		// TODO Auto-generated constructor stub
		mContext = context;
		this.map = map ;
		this.mSceton =mScetion ;
		this.citys = citys;
	}

	@Override
	public Object getItem(int section, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int section, int position) {
		// TODO Auto-generated method stub
		return 0;
	}
/*
 * 头数量
 * 
 */
	@Override
	public int getSectionCount() {
		// TODO Auto-generated method stub
		return mSceton.size();
	}
/*
 * 
 *  头下面单个项目数量
 * 
 */
	@Override
	public int getCountForSection(int section) {
		// TODO Auto-generated method stub
		return map.get(mSceton.get(section)).size();
	}
/*
 * 
 * listView的布局
 * 
 */
	@Override
	public View getItemView(int section, int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		if (convertView != null) {
			view = convertView ; 
			
		}
		else {
			view = View.inflate(mContext, R.layout.contentlistview, null);
		}
		TextView textView = (TextView) view.findViewById(R.id.citylist);
		textView.setText(map.get(mSceton.get(section)).get(position).getCity().toString());
		return view;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		if (convertView != null) {
			view = convertView ; 
			
		}
		else {
			view = View.inflate(mContext, R.layout.headview, null);
		}
		TextView textView = (TextView) view.findViewById(R.id.headlist);
		textView.setText(mSceton.get(section).toString());
		return view;
	}

	@Override
	public int getPositionInSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return super.getPositionInSectionForPosition(position);
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		// TODO Auto-generated method stub
		for (int i = 0,n=citys.size(); i < n; i++) {
			char firstP = citys.get(i).getFirstPY().charAt(0);
			if (firstP == sectionIndex) {
				System.out.println(i);
				return i;
				
			}
		}
	return -1;
	   
	}
		
}
