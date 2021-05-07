package com.weatherapp.network

import com.weatherapp.models.ResponseWeather
import retrofit.Call
import retrofit.http.GET

/**
 * An Interface which defines the HTTP operations Functions.
 */
interface WeatherService {
    @GET("2.5/box/city?bbox=12,32,15,37,10;appid=b6907d289e10d714a6e88b30761fae22")
    fun getWeather(): Call<ResponseWeather>
}