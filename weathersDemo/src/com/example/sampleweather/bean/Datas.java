package com.example.sampleweather.bean;

import java.util.ArrayList;

public class Datas {	
	public ArrayList<HeWeather>  HeWeather5 ;
	
	
	
	public class HeWeather{
	private Aqi aqi;
	private Basic basic;
	private Now now;
	private String status;
	private Suggestions suggestion;
	public Aqi getAqi() {
		return aqi;
	}
	public void setAqi(Aqi aqi) {
		this.aqi = aqi;
	}
	public Basic getBasic() {
		return basic;
	}
	public void setBasic(Basic basic) {
		this.basic = basic;
	}
	public Now getNow() {
		return now;
	}
	public void setNow(Now now) {
		this.now = now;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Suggestions getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(Suggestions suggestion) {
		this.suggestion = suggestion;
	}
	@Override
	public String toString() {
		return "Heweather [aqi=" + aqi + ", basic=" + basic + ", now=" + now
				+ ", status=" + status + ", suggestion=" + suggestion + "]";
	}
	
	
	}



	@Override
	public String toString() {
		return "Datas [HeWeather5=" + HeWeather5 + "]";
	}



	

	
}
