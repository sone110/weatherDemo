package com.example.sampleweather.utils;

import java.security.PublicKey;
import java.util.ArrayList;

import javax.security.auth.PrivateCredentialPermission;

import android.os.Message;

import com.example.sampleweather.bean.WeatherData;
import com.example.sampleweather.fragment.ContentViewFragment;
import com.example.sampleweather.viewpage.WeatherView;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class DataUtils {

 
	public  ArrayList<WeatherData> data;   
	public  void  getDatafrom(String string){
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, string, new RequestCallBack<String>() {

			private WeatherData weatherData;
			private  String result;

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				result = responseInfo.result;
				
				gsonUtils(result);
//				CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
				
			    
			}
		 
		@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
	
    }
	   private  void  gsonUtils(String string) {
			// TODO Auto-generated constructor stub
		    Gson gson = new Gson();
		    WeatherData weatherData	 = gson.fromJson(string, WeatherData.class);  
		    data = new ArrayList<WeatherData>();
		    data.add(weatherData);
 		 
	}
}
