package com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class Rain{

	@SerializedName("3h")
	private double jsonMember3h;

	public double getJsonMember3h(){
		return jsonMember3h;
	}
}