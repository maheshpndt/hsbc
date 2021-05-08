package com.weatherapp.repository

import com.weatherapp.models.ResponseWeather
import com.weatherapp.api.WeatherApiHelper
import io.reactivex.Single

class MainRepository(private val apiHelper: WeatherApiHelper) {
    fun getWeatherList() : Single<ResponseWeather> {
        return apiHelper.getWeatherList()
    }
}