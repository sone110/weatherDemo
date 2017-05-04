package com.example.sampleweather;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.sampleweather.bean.City;
import com.example.sampleweather.utils.CopyDataBase;
import com.example.sampleweather.utils.PinyinComparator;
import com.example.sampleweather.utils.PreferenceUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Handler;
import android.preference.Preference;



public class Application extends android.app.Application {

	public static ArrayList<EventHandler> mListeners = new ArrayList<EventHandler>();
	private List<City> mCityList;
	private static final String FORMAT = "^[a-z,A-Z].*$";
	private ArrayList<String> mSections;
	private HashMap<String, List<City>> mMap;
	private ArrayList<Integer> mPositions;
	private HashMap<String, String> mCityID;	
	private CopyDataBase mCityDB;
	private boolean isCityCopyfinished;
	private static Application mApplication;
	private static final int CITYCOPYFINISHED = 0;
	private static final int UPDATE = 1;
	private String[] city = null;
	private PreferenceUtils preferenceUtils  = new PreferenceUtils();
	private SlidingMenu slidingMenu;
	
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case CITYCOPYFINISHED:
				isCityCopyfinished = true ; 
//				遍历所有继承接口，执行onCityComplite（）方法
				if (mListeners.size() > 0)// 通知接口完成加载
					System.out.println(mListeners.size());
					for (EventHandler handler : mListeners) {
						handler.onCityComplite();
					}
				break;
			
			default:
				break;
			}
			
		}
	};

	private boolean isfisrtcoming;
	private long minute;
//获得实例化的Application
	public  static synchronized  Application getInstance(){
		
		return mApplication ;
	}
//	确保外部只有一个mcityDB实例
	public synchronized CopyDataBase getCityDB() {
		if (mCityDB == null)
			mCityDB = OpenDB();
		return mCityDB;
	}
//打开数据库操作
	private CopyDataBase OpenDB(){   
		final String DATABASE_PATH="data/data/"+ getPackageName() + "/databases/";  
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
				InputStream inputStream=getAssets().open("city.db"); 
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

		return new CopyDataBase(this, databaseFile) ;
	}
//	android app入口
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		初始化数据
		initData();
	}

   private void initData() {
	// TODO Auto-generated method stub
	   mApplication = this;
//	   初始化城市列表
	   city = preferenceUtils.getStrings(mApplication, "city");
	   isfisrtcoming    = PreferenceUtils.getBoolean(Application.this, "FIRST_COMING", true);
	   initCityList();
//	   System.out.println(city.length);
	   comparetime();
	
	
    }

    public void comparetime() {
	// TODO Auto-generated method stub
    	String lastTime = PreferenceUtils.getTime(Application.this, "time", "0");
		long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();  
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date d1=new Date(time);  
        String t1=format.format(d1);
//        System.out.println(t1);
        PreferenceUtils.setTime(Application.this, "time", t1);
        try {
        	Date beginTime=format.parse(lastTime);
			Date endTime=format.parse(t1);
//			分钟间隔
		   minute = setMinute((endTime.getTime() - beginTime.getTime())/(1000*60)) ;
		    
		   
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	private void initCityList() {
	// TODO Auto-generated method stub
    	mCityList = new ArrayList<City>();
    	mSections = new ArrayList<String>();
    	mMap = new HashMap<String, List<City>>();
		mPositions = new ArrayList<Integer>();
		mCityID = new HashMap<String, String>();
		mCityDB = OpenDB() ;
		new Thread( new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				isCityCopyfinished = false ;
				prepareCityList();
				handler.sendEmptyMessage(CITYCOPYFINISHED);
			}
		} ).start();
	
    }
	private boolean prepareCityList() {
		// TODO Auto-generated method stub
		mCityList  = mCityDB.getAllCity();
		Collections.sort(mCityList , new PinyinComparator());
//		遍历city，按首字母分类
		for (City city  : mCityList) {
//			获取首字母的数据
			String firstName = city.getFirstPY();
			String id = city.getNumber();
			mCityID.put(city.getCity(), id);
			if (firstName.matches(FORMAT)) {
//				如果首字母已经包含则添加city到citylist然后关联map
				if (mSections.contains(firstName)) {
					mMap.get(firstName).add(city);
//					如果没有首字母则先添加首字母，然后添加城市到map中
				} else {
					mSections.add(firstName);
					List<City> list = new ArrayList<City>();
					list.add(city);
					mMap.put(firstName, list);
				}
			} else {
				if (mSections.contains("#")) {
					mMap.get("#").add(city);
				} else {
					mSections.add("#");
					List<City> list = new ArrayList<City>();
					list.add(city);
					mMap.put("#", list);
				}
			}
		
		}
		return true ;
		
	}
	
	
	
	


	public static abstract interface EventHandler {
		public abstract void onCityComplite();

//		public abstract void onNetChange();

	}

	public List<City> getmCityList() {
		return mCityList;
	}
	public ArrayList<String> getmSections() {
		return mSections;
	}
	public HashMap<String, List<City>> getmMap() {
		return mMap;
	}
	public ArrayList<Integer> getmPositions() {
		return mPositions;
	}
	
	public CopyDataBase getmCityDB() {
		return mCityDB;
	}
	public boolean isCityCopyfinished() {
		return isCityCopyfinished;
	}
	public String[] getCity() {
		return city;
	}
	public  void setCity(String[] city ){
		this.city = city;
	}
	public PreferenceUtils getPreferenceUtils() {
		return preferenceUtils;
	}
	public SlidingMenu getSlidingMenu() {
		return slidingMenu;
	}
	public void setSlidingMenu(SlidingMenu slidingMenu) {
		this.slidingMenu = slidingMenu;
	}
	public HashMap<String, String> getmCityID() {
		return mCityID;
	}
	public long getMinute() {
		return minute;
	}
	public long setMinute(long minute) {
		return this.minute = minute;
	}
	
	
}
 
