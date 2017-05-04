package com.example.sampleweather.utils;

import java.util.Comparator;

import com.example.sampleweather.bean.City;

/**
 * 
 * @author xiaanming
 *排序
 */
public class PinyinComparator implements Comparator<City> {

	public int compare(City o1, City o2) {
		
		if (o1.getAllPY().equals("@")
				|| o2.getAllPY().equals("#")) {
			return -1;
		} else if (o1.getAllPY().equals("#")
				|| o2.getAllPY().equals("@")) {
			return 1;
		} else {
			return o1.getAllPY().compareTo(o2.getAllPY());
		}
	}

}
