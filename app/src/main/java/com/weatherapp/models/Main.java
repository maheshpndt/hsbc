package com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	private double temp;

	@SerializedName("temp_min")
	private double tempMin;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private double pressure;

	@SerializedName("temp_max")
	private double tempMax;

	@SerializedName("grnd_level")
	private double grndLevel;

	@SerializedName("sea_level")
	private double seaLevel;

	public double getTemp(){
		return temp;
	}

	public double getTempMin(){
		return tempMin;
	}

	public int getHumidity(){
		return humidity;
	}

	public double getPressure(){
		return pressure;
	}

	public double getTempMax(){
		return tempMax;
	}

	public double getGrndLevel(){
		return grndLevel;
	}

	public double getSeaLevel(){
		return seaLevel;
	}
}