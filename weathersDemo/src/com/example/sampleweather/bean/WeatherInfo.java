package com.example.sampleweather.bean;

import java.util.ArrayList;

public class WeatherInfo {

		
			public String aqi;
			public String city;
			public String ganmao;
			public String wendu;
			public ArrayList<DayData> forecast;
//			
//			
			@Override
			public String toString() {
				return "WeatherInfo [aqi=" + aqi + ", city=" + city
						+ ", ganmao=" + ganmao + ", wendu=" + wendu
						+ ", forecast=" + forecast + "]";
			}
			

}
