package com.example.sampleweather.ui;

import android.text.StaticLayout;

public class SwipeLayoutManager {
	private SwipeLayoutManager (){}
	
	
	
	private static SwipeLayoutManager mInstance = new SwipeLayoutManager();
	public static SwipeLayoutManager getInstance(){
		return mInstance ;
	}
	
	
	
	private SwipeDelete currentDelete ;
	public void setCurrentDelete(SwipeDelete currentDelete) {
		this.currentDelete = currentDelete;
	}
	
	
	/**
	 * 清空当前所记录的已经打开的layout
	 */
	public void clearCurrentLayout(){
		currentDelete = null;
	}
	
	
	/**
	 * 关闭当前已经打开的SwipeLayout
	 */
	public void closeCurrentDelete(){
		if(currentDelete!=null){
			currentDelete.close();
		}
	}
	
	/**
	 * 判断当前是否应该能够滑动，如果没有打开的，则可以滑动。
	 * 如果有打开的，则判断打开的layout和当前按下的layout是否是同一个
	 * 
	 * @return
	 */
	public boolean isShouldSwipe(SwipeDelete swipeDelete){
		
		if (currentDelete == null) {
//			没有打开的
			return true;
			
		}else {
			return currentDelete == swipeDelete;
		}
	}

}
