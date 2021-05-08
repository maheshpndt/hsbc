package com.weatherapp.interface_

import com.weatherapp.models.ResponseWeather
import io.reactivex.Single
import retrofit.Call

/**
 * An Interface which defines the HTTP operations Functions.
 */
interface WeatherService {
    fun getWeather(): Single<ResponseWeather>
}