package com.udaychugh.weatherapp.mapper

import com.udaychugh.weatherapp.data.model.Weather
import com.udaychugh.weatherapp.data.source.local.entity.DBWeather

class WeatherMapperLocal : BaseMapper<DBWeather, Weather> {
    override fun transformToDomain(type: DBWeather): Weather = Weather (
        uId = type.uId,
        cityId = type.cityId,
        name = type.cityName,
        wind = type.wind,
        networkWeatherDescription = type.networkWeatherDescription,
        networkWeatherCondition = type.networkWeatherCondition
    )

    override fun transformToDto(type: Weather): DBWeather = DBWeather (
        uId = type.uId,
        cityId = type.cityId,
        cityName = type.name,
        wind = type.wind,
        networkWeatherDescription = type.networkWeatherDescription,
        networkWeatherCondition = type.networkWeatherCondition
            )
}