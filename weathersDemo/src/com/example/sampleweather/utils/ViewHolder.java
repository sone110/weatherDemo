package com.example.sampleweather.utils;

import android.util.SparseArray;
import android.view.View;

	public class ViewHolder {
	    public static <T extends View> T get(View view, int id) {
//	    	获取getTag
	        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
//	        如果viewHolder为空则重新创建并打标记
	        if (viewHolder == null) {
	            viewHolder = new SparseArray<View>();
	            view.setTag(viewHolder);
	        }
	        
	        View childView = viewHolder.get(id);
	        if (childView == null) {
	            childView = view.findViewById(id);
	            viewHolder.put(id, childView);
	        }
	        return (T) childView;
	    }
	}

