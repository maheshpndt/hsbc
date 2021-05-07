package com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class Wind{

	@SerializedName("deg")
	private double deg;

	@SerializedName("speed")
	private double speed;

	@SerializedName("var_beg")
	private int varBeg;

	@SerializedName("var_end")
	private int varEnd;

	public double getDeg(){
		return deg;
	}

	public double getSpeed(){
		return speed;
	}

	public int getVarBeg(){
		return varBeg;
	}

	public int getVarEnd(){
		return varEnd;
	}
}