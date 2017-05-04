package com.example.sampleweather.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.sampleweather.bean.City;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

public class CopyDataBase {
	
	
	public static final  String  DATEBASE_NAME = "city.db";
	public static final int DATABASE_VERSION = 1;
	public static final String CITY_TABLE_NAME = "city";
	private Context context;
	private SQLiteDatabase database;
	private Handler handler;
	private List<City> citylist = new ArrayList<City>();

	public CopyDataBase(Context context , String path)
	{
		this.context = context;
		database  =  SQLiteDatabase.openOrCreateDatabase(path, null);
//		getAllCity();
		
	}
   public  CopyDataBase OpenDB(){   
	   final String DATABASE_PATH="data/data/"+ context.getPackageName() + "/databases/";  
       String databaseFile=DATABASE_PATH+"city.db";  
       //创建databases目录（不存在时）  
       File file=new File(DATABASE_PATH);  
       if(!file.exists()){  
           file.mkdirs();  
       }  
       //判断数据库是否存在  
       if (!new File(databaseFile).exists()) {  
           //把数据库拷贝到/data/data/<package_name>/databases目录下  
           try {  
               FileOutputStream fileOutputStream = new FileOutputStream(databaseFile);  
               //数据库放assets目录下  
               InputStream inputStream=context.getAssets().open("city.db"); 
               byte[] buffer = new byte[1024];  
               int readBytes = 0;  
 
               while ((readBytes = inputStream.read(buffer)) != -1)  
                   {
            	      
            	      fileOutputStream.write(buffer, 0, readBytes);  
                      fileOutputStream.flush();
                   }
 
                   inputStream.close();  
                   fileOutputStream.close();  
               } catch (IOException e) {  
           }  
       }  
      
	 return new CopyDataBase(context, databaseFile) ;
   }
   
   public List<City> getAllCity() {
				List<City> list = new ArrayList<City>();
				Cursor c = database.rawQuery("SELECT * from " + CITY_TABLE_NAME, null);
				while (c.moveToNext()) {
					String province = c.getString(c.getColumnIndex("province"));
					String city = c.getString(c.getColumnIndex("city"));
					String number = c.getString(c.getColumnIndex("number"));
					String allPY = c.getString(c.getColumnIndex("allpy"));
					String allFirstPY = c.getString(c.getColumnIndex("allfirstpy"));
					String firstPY = c.getString(c.getColumnIndex("firstpy"));
					City item = new City(province, city, number, firstPY, allPY,
							allFirstPY);
					list.add(item);	
					
				}
			    c.close();
			  database.close();
			  return list;
			} 
   
}
