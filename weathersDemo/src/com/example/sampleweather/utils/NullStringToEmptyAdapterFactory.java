package com.example.sampleweather.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {

	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		// TODO Auto-generated method stub
	     Class<T> rawType = (Class<T>) type.getRawType();
	        if (rawType != String.class) {
	            return null;
	        }
	        return (TypeAdapter<T>) new StringNullAdapter();
	    }
	

}
