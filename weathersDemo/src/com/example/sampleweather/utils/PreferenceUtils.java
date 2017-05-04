package com.example.sampleweather.utils;

import android.R.string;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {



	public static boolean getBoolean(Context context,String key , boolean defValue ){
		
		SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	public static void setBoolean(Context context,String key , boolean defValue ){
		
		SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, defValue).commit();
	}
    public static int getInt(Context context, String key , int defValue ){
		
		SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}
	public static void setInt(Context context,String key , int defValue ){
		
		SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putInt(key, defValue).commit();
	}
	public static String getString(Context context, String key , String defValue ){
			
	    SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}
	public static void setString(Context context,String key , String defValue ){
			
		SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putString(key, defValue).commit();
	}	
	public static void setStrings(Context context , String key ,String[] defValue){
		
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String spiltString = "#";
		String str = "";
		
		if (defValue.length>0 && defValue != null) {
			
			for (String string : defValue) {
				str += string  ;
				str += spiltString ;
			}
			
			sp.edit().putString(key, str).commit();
		}
		
	}
	
	public static String[] getStrings(Context context, String key ){
		
		String spiltString = "#";
		String [] strings  ;
		String string ;
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		string = sp.getString(key, "");
		strings = string.split(spiltString);
		return strings;
	}
	
    public static String getTime(Context context, String key , String defValue ){
		
	    SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}
	public static void setTime(Context context,String key , String Value ){
			
		SharedPreferences  sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putString(key, Value).commit();
	}	

}
