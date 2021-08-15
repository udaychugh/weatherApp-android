package com.udaychugh.weatherapp.mapper

import com.udaychugh.weatherapp.data.model.WeatherForecast
import com.udaychugh.weatherapp.data.source.local.entity.DBWeatherForeast

class WeatherForecastMapperLocal :
    BaseMapper<List<DBWeatherForecast>, List<WeatherForecast>> {
        override fun transformToDomain(type: List<DBWeatherForecast>): List<WeatherForecast> {
            return type.map{dbWeatherForecast ->
                WeatherForecast(
                    dbWeatherForecast.id,
                    dbWeatherForecast.date,
                    dbWeatherForecast.wind,
                    dbWeatherForecast.networkWeatherDescriptions,
                    dbWeatherForecast.networkWeatherCondition
                )
            }
        }

    override fun transformToDto(type: List<WeatherForecast>): List<DBWeatherForecast> {
        return type.map {weatherForecast ->
            weatherForecast.uID,
            weatherForecast.date,
            weatherForecast.wind,
            weatherForecast.networkWeatherDescription,
            weatherForecast.networkWeatherCondition
        }
    }
    }
