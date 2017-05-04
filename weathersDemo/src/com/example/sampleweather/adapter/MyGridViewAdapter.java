package com.example.sampleweather.adapter;

import java.util.List;
import java.util.Map;

import com.example.sampleweather.R;
import com.example.sampleweather.utils.NewString;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyGridViewAdapter extends BaseAdapter {

	
	private Context mContext;
	private NewString newString;
	private TextView textView;

	private List<Map<String, Object>> mDayWeatherInfos;

	private LayoutInflater mInflater;
	

	public MyGridViewAdapter(Context contex ,List<Map<String,Object>> mDayWeatherInfos) {
		super();
		// TODO Auto-generated constructor stub
		mContext = contex;
		this.mDayWeatherInfos = mDayWeatherInfos;
		
	}
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  mDayWeatherInfos.size() ;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto- method stub
		ViewHolder holder ;
	    if (convertView != null ) {
			holder = (ViewHolder) convertView.getTag();
		}
	    else {
	    	
	    	holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.forcast, null);
//	    	convertView = LinearLayout.inflate(mContext, R.layout.activity_weatherinfo, null);
			holder.data = (TextView) convertView.findViewById(R.id.datatime);
			holder.icon = (ImageView) convertView.findViewById(R.id.dayforcasticon);
			holder.temp = (TextView) convertView.findViewById(R.id.datatempinfo);
			convertView.setTag(holder);
		}
	    holder.data.setText(mDayWeatherInfos.get(position).get("weekendText").toString());
	    holder.icon.setImageResource((Integer) mDayWeatherInfos.get(position).get("weekendImage"));
	    holder.temp.setText(mDayWeatherInfos.get(position).get("weekendTemp").toString());
		return convertView;
	}
	static class ViewHolder {
		ImageView icon;
		TextView data,temp;
	}
	
	
}
