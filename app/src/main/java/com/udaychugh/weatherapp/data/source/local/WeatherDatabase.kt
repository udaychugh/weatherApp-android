package com.udaychugh.weatherapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udaychugh.weatherapp.data.source.local.dao.WeatherDao
import com.udaychugh.weatherapp.data.source.local.entity.DBWeather
import com.udaychugh.weatherapp.data.source.local.entity.DBWeatherForecast

@Database(entities = [DBWeather::class, DBWeatherForecast::class], version = 1, exportSchema = true)
@TypeConverters(
    ListNetworkWeatherDescriptionConverter::class
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}
