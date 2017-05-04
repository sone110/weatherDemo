package com.example.sampleweather.bean;

import java.util.concurrent.locks.Condition;

public class Now {
	private Cond cond;

	private String fl;

	private String hum;

	private String pcpn;

	private String pres;

	private String tmp;

	private String vis;

	private Wind wind;

	public void setCond(Cond cond){
	this.cond = cond;
	}
	public Cond getCond(){
	return this.cond;
	}
	public void setFl(String fl){
	this.fl = fl;
	}
	public String getFl(){
	return this.fl;
	}
	public void setHum(String hum){
	this.hum = hum;
	}
	public String getHum(){
	return this.hum;
	}
	public void setPcpn(String pcpn){
	this.pcpn = pcpn;
	}
	public String getPcpn(){
	return this.pcpn;
	}
	public void setPres(String pres){
	this.pres = pres;
	}
	public String getPres(){
	return this.pres;
	}
	public void setTmp(String tmp){
	this.tmp = tmp;
	}
	public String getTmp(){
	return this.tmp;
	}
	public void setVis(String vis){
	this.vis = vis;
	}
	public String getVis(){
	return this.vis;
	}
	public void setWind(Wind wind){
	this.wind = wind;
	}
	public Wind getWind(){
	return this.wind;
	}
	
	public class Wind{
		private String deg;

		private String dir;

		private String sc;

		private String spd;

		public void setDeg(String deg){
		this.deg = deg;
		}
		public String getDeg(){
		return this.deg;
		}
		public void setDir(String dir){
		this.dir = dir;
		}
		public String getDir(){
		return this.dir;
		}
		public void setSc(String sc){
		this.sc = sc;
		}
		public String getSc(){
		return this.sc;
		}
		public void setSpd(String spd){
		this.spd = spd;
		}
		public String getSpd(){
		return this.spd;
		
		}
		@Override
		public String toString() {
			return "Wind [deg=" + deg + ", dir=" + dir + ", sc=" + sc
					+ ", spd=" + spd + "]";
		}
	}
	
	public class Cond{
		
		private String code;

		private String txt;

		public void setCode(String code){
		this.code = code;
		}
		public String getCode(){
		return this.code;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}
		@Override
		public String toString() {
			return "Cond [code=" + code + ", txt=" + txt + "]";
		}
		
	}

	@Override
	public String toString() {
		return "Now [cond=" + cond + ", fl=" + fl + ", hum=" + hum + ", pcpn="
				+ pcpn + ", pres=" + pres + ", tmp=" + tmp + ", vis=" + vis
				+ ", wind=" + wind + "]";
	}
	


}
