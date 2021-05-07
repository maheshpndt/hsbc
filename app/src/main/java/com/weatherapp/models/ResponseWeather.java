package com.weatherapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseWeather{

	@SerializedName("calctime")
	private double calctime;

	@SerializedName("cnt")
	private int cnt;

	@Override
	public String toString() {
		return "ResponseWeather{" +
				"calctime=" + calctime +
				", cnt=" + cnt +
				", cod='" + cod + '\'' +
				", list=" + list +
				'}';
	}

	@SerializedName("cod")
	private String cod;

	@SerializedName("list")
	private List<ListItem> list;

	public double getCalctime(){
		return calctime;
	}

	public int getCnt(){
		return cnt;
	}

	public String getCod(){
		return cod;
	}

	public List<ListItem> getList(){
		return list;
	}
}