package com.example.sampleweather.utils;

public class NewString {

	private String strings;
	public String NewString(String string) {
		// TODO Auto-generated constructor stub
		       strings = "";
	        for (int i=0; i< string.length();i++){
				if(Character.isDigit(string.charAt(i))){
					strings+=string.charAt(i);
				}
		  }
	        return  strings ;
		
	}

}
