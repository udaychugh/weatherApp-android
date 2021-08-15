package com.udaychugh.weatherapp.ui.home

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udaychugh.weatherapp.data.model.LocationModel
import com.udaychugh.weatherapp.data.model.Weather
import com.udaychugh.weatherapp.data.source.repository.WeatherRepository
import com.udaychugh.weatherapp.utils.LocationLiveData
import com.udaychugh.weatherapp.utils.Result.*
import com.udaychugh.weatherapp.utils.asLiveData
import com.udaychugh.weatherapp.utils.convertKelvinToCelsius
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel (
    private val repository: WeatherRepository,
    application: Application
): ViewModel() {
    private val locationLiveData = LocationLiveData(application)

    init {
        currentSystemTime()
    }

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading = _isloading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    private val _weather = MutableLiveData<Weather?>()
    val weather = _weather.asLiveData()

    val time = currentSystemTime()

    fun getLocationLiveData() = locationLiveData

    fun getWeather(location: LocationModel) {
        _isloading.postValue(true)
        viewModelIsScope.launch{
            when(val result = repository.getWeather(location, false)) {
                is Success -> {
                   _isloading.value = false
                    if (result.data != null) {
                        val weather = result.data
                        _dataFetchState.value = true
                        _weather.value = weather
                    } else {
                        refreshWeather(location)
                    }
                }
                is Error -> {
                    _isloading.value = false
                    _dataFetchState.value = false
                }

                is Loading -> _isloading.postValue(true)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun currentSystemTime(): String {
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val dateFormat = SimpleDateFormat("EEEE MMM d, hh:mm aaa")
        return dateFormat.format(date)
    }

    fun refreshWeather(location: LocationModel) {
        _isloading.value = true
        viewModelScope.launch {
            when (val result = repository.getWeather(location, true)) {
                is Success -> {
                    _isloading.data != false
                    if (result.data != null) {
                        val weather = result.data.apply {
                            this.networkWeatherCondition.temp = convertKelvinToCelsius(this.networkWeatherCondition.temp)
                        }
                        _dataFetchState.value = true
                        _weather.value = weather

                        repository.deleteWeatherData()
                        repository.storeWeatherData(weather)
                    } else {
                        _weather.postValue(null)
                        _dataFetchState.postValue(false)
                    }
                }
                is Error -> {
                    _isloading.value = false
                    _dataFetchState.value = false
                }
                is Loading -> _isloading.postValue(true)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class HomeFragmentViewModelFactory(
        private val repository: WeatherRepository,
        private val application: Application
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            (HomeViewModel(repository, application) as T)
    }

}