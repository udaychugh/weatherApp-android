package com.udaychugh.weatherapp.data.source.remote

import com.udaychugh.weatherapp.data.model.LocationModel
import com.udaychugh.weatherapp.data.model.NetworkWeather
import com.udaychugh.weatherapp.data.model.NetworkWeatherForecast
import com.udaychugh.weatherapp.utils.Result

interface WeatherRemoteDataSource {
    suspend fun getWeather(location: LocationModel): Result<NetworkWeather>

    suspend fun getWeatherForecast(cityId: Int): Result<List<NetworkWeatherForecast>>

    suspend fun getSearchWeather(query: String): Result<NetworkWeather>
}