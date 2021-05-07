package com.weatherapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("dt")
	private int dt;

	@SerializedName("coord")
	private Coord coord;

	@Override
	public String toString() {
		return "ListItem{" +
				"dt=" + dt +
				", coord=" + coord +
				", name='" + name + '\'' +
				", weather=" + weather +
				", main=" + main +
				", id=" + id +
				", clouds=" + clouds +
				", wind=" + wind +
				", rain=" + rain +
				'}';
	}

	@SerializedName("name")
	private String name;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("main")
	private Main main;

	@SerializedName("id")
	private int id;

	@SerializedName("clouds")
	private Clouds clouds;

	@SerializedName("wind")
	private Wind wind;

	@SerializedName("rain")
	private Rain rain;

	public int getDt(){
		return dt;
	}

	public Coord getCoord(){
		return coord;
	}

	public String getName(){
		return name;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public Main getMain(){
		return main;
	}

	public int getId(){
		return id;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public Wind getWind(){
		return wind;
	}

	public Rain getRain(){
		return rain;
	}
}