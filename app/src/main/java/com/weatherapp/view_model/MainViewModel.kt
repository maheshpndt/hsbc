package com.weatherapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weatherapp.models.ResponseWeather
import com.weatherapp.utils.Resource
import com.weatherapp.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : ViewModel(){
    private val weather = MutableLiveData<Resource<ResponseWeather>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        weather.postValue(Resource.loading(null))
        compositeDisposable.add(
            repository.getWeatherList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weatherList ->
                    weather.postValue(
                        Resource.success(
                            weatherList
                        )
                    )
                }, { throwable ->
                    weather.postValue(
                        Resource.error(
                            "Something Went Wrong",
                            null
                        )
                    )
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getWeather(): LiveData<Resource<ResponseWeather>> {
        return weather
    }
}