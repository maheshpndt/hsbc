package com.weatherapp.api

import com.weatherapp.interface_.WeatherService

class WeatherApiHelper(private val apiHelper: WeatherService) {
    fun getWeatherList() = apiHelper.getWeather()
}