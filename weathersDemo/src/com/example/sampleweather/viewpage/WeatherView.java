package com.example.sampleweather.viewpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sampleweather.Application;
import com.example.sampleweather.Application.EventHandler;
import com.example.sampleweather.R;
import com.example.sampleweather.adapter.MyGridViewAdapter;
import com.example.sampleweather.bean.Datas;
import com.example.sampleweather.bean.DayData;
import com.example.sampleweather.bean.GlobleData;
import com.example.sampleweather.bean.WeatherData;
import com.example.sampleweather.fragment.ContentViewFragment;
import com.example.sampleweather.utils.LogUtils;
import com.example.sampleweather.utils.NewString;
import com.example.sampleweather.utils.NullStringToEmptyAdapterFactory;
import com.example.sampleweather.utils.PreferenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.loc.i;
import com.loc.s;

import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.tv.TvContentRating;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherView extends BasePage  {
    
	private static final int DATA_1 = 1;
	private static final int DATA_2 = 2;
	private String string;
	private WeatherData weatherDatas  ;
	private Handler handler;
	private ArrayList<DayData> mDayDatas;
	private MyGridViewAdapter mGridviewAdapter;
	private NewString newString= new NewString();
	private Datas mHeWeather ; 
	private String [] a =  new String[]{"a","b","c","d","e","f"};
	private String city;
	private String url;
	private String url1;
	private Application mApplication;
	private Gson gson;

	public WeatherView(Activity activity, String string,String city) {
		super(activity, string,city);
		// TODO Auto-generated constructor stub
		 this.string = string ;
		 this.city = city;
		 mApplication = Application.getInstance();
	}

	 
		
	@Override
	public void initdata() {
		// TODO Auto-generated method stub
		  url = GlobleData.URL+ string;
		  url1 = GlobleData.SUGEESTION + string +GlobleData.KEY;
		  gson = new GsonBuilder().serializeNulls().create();
		  LogUtils.i(url);
		  updateDatas();
//         getDatafrom(url , DATA_1);
//         getDatafrom(url1, DATA_2);
		  mRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RotateAnimation ra = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);  
                ra.setDuration(1000);  
                v.startAnimation(ra);  
                getDatafrom(url , DATA_1);
                getDatafrom(url1, DATA_2);
                Toast.makeText(mActivity, "数据刷新中", 0).show();
			}
		});
		
         handler =new  Handler(){
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		switch (msg.what) {
				case DATA_1:
					weatherDatas = (WeatherData) msg.obj;     	
	        		updataUI(); 
					break;

				case DATA_2:
					mHeWeather = (Datas) msg.obj;
					updataInfos();
					break;
					
				default:
					break;
				}
        		       	
        	}
        	 
         };

	     
	}
	
	private void updateDatas() {
		// TODO Auto-generated method stub
		if (mApplication.getMinute() > 30 || (PreferenceUtils.getString(mActivity, url, null)) ==null ) {
	         getDatafrom(url , DATA_1);
	         getDatafrom(url1, DATA_2);
		}
		else {
			String string1 = PreferenceUtils.getString(mActivity, url, null);
			String string2 = PreferenceUtils.getString(mActivity, url1, null);
//		    Toast.makeText(mActivity, string1, 0).show();
		    weatherDatas =  gson.fromJson(string1, WeatherData.class);
		    mHeWeather = gson.fromJson(string2, Datas.class);
		    updataInfos();
		    updataUI();
//			gsonUtils(string1, DATA_1);
//			gsonUtils(string2, DATA_2);
		}
	}



	protected void updataInfos() {
		// TODO Auto-generated method stub
//		if (mHeWeather.HeWeather5.get(0).getAqi().equals(null)) {
//			
//			mAqidate.setText("N/A");
//		}
//		else  {
//			
//			mAqidate.setText(mHeWeather.HeWeather5.get(0).getAqi().getCity().getAqi());
//		}
	   mAqi.setText(mHeWeather.HeWeather5.get(0).getSuggestion().air.brf);
	   mAqiInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().air.txt);
	   mCw.setText(mHeWeather.HeWeather5.get(0).getSuggestion().cw.brf);
	   mCwInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().cw.txt);
	   mDrsg.setText(mHeWeather.HeWeather5.get(0).getSuggestion().drsg.brf);
	   mDrsgInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().drsg.txt);
	   mTravl.setText(mHeWeather.HeWeather5.get(0).getSuggestion().trav.brf);
	   mTravlInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().trav.txt);
	   mSport.setText(mHeWeather.HeWeather5.get(0).getSuggestion().sport.brf);
	   mSportInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().sport.txt);
	   mFlu.setText(mHeWeather.HeWeather5.get(0).getSuggestion().flu.brf);
	   mFluInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().flu.txt);
	   mUv.setText(mHeWeather.HeWeather5.get(0).getSuggestion().uv.brf);
	   mUvInfo.setText(mHeWeather.HeWeather5.get(0).getSuggestion().uv.txt);

	   mFengli.setText(mHeWeather.HeWeather5.get(0).getNow().getWind().getSc().toString());
	   mFengxiang.setText(mHeWeather.HeWeather5.get(0).getNow().getWind().getDir().toString());
	   mNengjiandu.setText(mHeWeather.HeWeather5.get(0).getNow().getVis().toString()+"KM");
	   mShidu.setText(mHeWeather.HeWeather5.get(0).getNow().getHum().toString()+"%");
	   mJiangshui.setText(mHeWeather.HeWeather5.get(0).getNow().getPcpn().toString() +"mm");
	   
	}



	private TextView setText(String hum) {
		// TODO Auto-generated method stub
		return null;
	}



	protected void updataUI() {
		// TODO Auto-generated method stub		
		mCurrentCIty.setText(city);
		String high =weatherDatas.data.forecast.get(0).high;
		String low = weatherDatas.data.forecast.get(0).low;
		mNowIcon.setImageResource(mDayMapIcon.get(weatherDatas.data.forecast.get(0).type));
		mNowTemp.setText(weatherDatas.data.wendu.toString()+"℃");
		mTodayTemp.setText(newString.NewString(high)+"℃~"+newString.NewString(low)+"℃");
		mTianqi.setText(weatherDatas.data.forecast.get(0).type);
		LogUtils.i(weatherDatas.data.wendu.toString()+"℃");
//		System.out.println(weatherDatas.data.forecast.toString());
		mDayDatas = weatherDatas.data.forecast;
		mGridviewAdapter = new MyGridViewAdapter(mActivity, getData(mDayDatas));
		horizontal_layout();
		
		mGridView.setAdapter(mGridviewAdapter);

		
		
	}



	public  void  getDatafrom(final String string  ,final int i){
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, string, new RequestCallBack<String>() {

			
			private  String result;

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				result = responseInfo.result;				
				System.out.println(result );
				gsonUtils(result , i);
				PreferenceUtils.setString(mActivity, string, result);

				
			    
			}
		 
		@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(mActivity, "数据更新失败，请检查网络是否开启", 0).show();
			}
			
		});
	
	
    }
//	获取解析后的gson数据
	   public  void  gsonUtils(String string , int i) {
			// TODO Auto-generated constructor stub
		   if (i ==DATA_1) {
			
			   Gson gson = new GsonBuilder().serializeNulls().create();
			   WeatherData  weatherData	 = gson.fromJson(string, WeatherData.class);  
//		    System.out.println(weatherData);  
			   Message message =Message.obtain();
			   message.what = 1 ;
			   message.obj =weatherData;
			   handler.sendMessage(message);
		}
		   else if(i==DATA_2){
			
			    Gson gson = new GsonBuilder().serializeNulls().create();
				 Datas  weatherData	 = gson.fromJson(string, Datas.class);  
				    System.out.println(weatherData.toString());  
				    Message message =Message.obtain();
				    System.out.println(weatherData);
//				    Toast.makeText(mActivity, weatherData.toString(), 0).show();
				    message.what = 2 ;
				    message.obj =weatherData;
				    handler.sendMessage(message);
		}
	}
	
		private List<Map<String,Object>> getData(ArrayList<DayData> dayWeatherInfos){
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        for(DayData info:dayWeatherInfos){
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("weekendText", newString.NewString(info.date.toString())+"日");
	            Integer typeImageId = mTypeIcon.get(info.type);
//	           System.out.println(typeImageId); 
//	            System.out.println(info.type.toString());
	            if(typeImageId != null) {
	                map.put("weekendImage", typeImageId);//info.getType()
	                
	            }else{
	                map.put("weekendImage", R.drawable.ww0);
	            }
	            map.put("weekendTemp", newString.NewString(info.high.toString())+ "~" +newString.NewString(info.low.toString())+"℃");
	            list.add(map);
	        }
	        return list;
		}
		
//		绑定大图标
		private Map<String, Integer> mTypeIcon = getTypeMap();
		private Map<String, Integer> getTypeMap(){
			 Map<String,Integer> map = new HashMap<String,Integer>();
		        map.put("晴",R.drawable.ww0);
		        map.put("多云",R.drawable.ww1);
		        map.put("阴",R.drawable.ww2);
		        map.put("阵雨",R.drawable.ww3);
		        map.put("雷阵雨",R.drawable.ww4);
		        map.put("小雨",R.drawable.ww7);
		        map.put("中雨",R.drawable.ww8);
		        map.put("大雨",R.drawable.ww19);
		        map.put("暴雨",R.drawable.ww9);
		        map.put("大暴雨",R.drawable.ww10);
		        map.put("小雪",R.drawable.ww14);
		        map.put("大雪",R.drawable.ww16);
		        map.put("暴雪",R.drawable.ww17);
		        map.put("沙尘暴",R.drawable.ww45);
		        map.put("雾",R.drawable.ww18);
		        map.put("雨夹雪",R.drawable.ww6);
		        map.put("阵雪",R.drawable.ww5);
		        map.put("中雪",R.drawable.ww15);

		       return map;
			
		}
//		绑定小图标
		private Map<String, Integer> mDayMapIcon = getDayIconMap();
		private Map<String, Integer> getDayIconMap(){
			 Map<String,Integer> map = new HashMap<String,Integer>();
		        map.put("晴",R.drawable.org3_ww0);
		        map.put("多云",R.drawable.org3_ww1);
		        map.put("阴",R.drawable.org3_ww2);
		        map.put("阵雨",R.drawable.org3_ww3);
		        map.put("雷阵雨",R.drawable.org3_ww4);
		        map.put("小雨",R.drawable.org3_ww7);
		        map.put("中雨",R.drawable.org3_ww8);
		        map.put("大雨",R.drawable.org3_ww19);
		        map.put("暴雨",R.drawable.org3_ww9);
		        map.put("大暴雨",R.drawable.org3_ww10);
		        map.put("小雪",R.drawable.org3_ww14);
		        map.put("大雪",R.drawable.org3_ww16);
		        map.put("暴雪",R.drawable.org3_ww17);
		        map.put("沙尘暴",R.drawable.org3_ww45);
		        map.put("雾",R.drawable.org3_ww18);
		        map.put("雨夹雪",R.drawable.org3_ww6);
		        map.put("阵雪",R.drawable.org3_ww5);
		        map.put("中雪",R.drawable.org3_ww15);

		        return map;
			
		}
//		GridView 布局
		public void horizontal_layout(){
	        int size = mDayDatas.size();
	        DisplayMetrics dm = new DisplayMetrics();
	        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	        float density = dm.density;
	        WindowManager wm = (WindowManager) mActivity
                    .getSystemService(Context.WINDOW_SERVICE);
 
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            
	        System.out.println("density="+density+width + height);
//	        int allWidth = (int) (110 * size * density);
	        int itemWidth = (int) (width/5);
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	        mGridView.setLayoutParams(params);// 设置GirdView布局参数
	        mGridView.setColumnWidth(itemWidth);// 列表项宽
	        mGridView.setHorizontalSpacing(size);// 列表项水平间距
	        mGridView.setStretchMode(GridView.NO_STRETCH);
	        mGridView.setSelector(new  ColorDrawable(Color.TRANSPARENT));
	        mGridView.setNumColumns(size);//总长度
	    }


}
