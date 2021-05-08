package com.weatherapp.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.weatherapp.interface_.WeatherService
import com.weatherapp.models.ResponseWeather
import io.reactivex.Single

class WeatherServiceImplementation : WeatherService {

    override fun getWeather(): Single<ResponseWeather> {
        return Rx2AndroidNetworking
                .get("https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10;appid=b6907d289e10d714a6e88b30761fae22")
                .build()
                .getObjectSingle(ResponseWeather::class.java);
    }
}