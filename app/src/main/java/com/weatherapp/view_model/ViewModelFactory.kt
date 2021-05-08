package com.weatherapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.api.WeatherApiHelper
import com.weatherapp.repository.MainRepository

class ViewModelFactory(private val apiHelper: WeatherApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                MainRepository(
                    apiHelper
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}