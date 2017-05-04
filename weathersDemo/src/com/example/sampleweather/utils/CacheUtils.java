package com.example.sampleweather.utils;

import android.content.Context;

public class CacheUtils {
	
	public static void  setCache(String url  , String json , Context ctx){
		PreferenceUtils.setString(ctx, "url", json);
	}
    public static String getCache(String url , Context ctx){
    	
    	   return PreferenceUtils.getString(ctx, "url", null);
    }
	
}
