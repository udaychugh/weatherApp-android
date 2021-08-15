package com.udaychugh.weatherapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udaychugh.weatherapp.data.model.NetworkWeatherCondition
import com.udaychugh.weatherapp.data.model.NetworkWeatherDescription
import com.udaychugh.weatherapp.data.model.Wind

@Entity(tableName = "weather_forecast")
class DBWeatherForecast(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val date: String,

    @Embedded
    val wind: Wind,

    @ColumnInfo(name = "weather_description")
    val networkWeatherDescriptions: List<NetworkWeatherDescription>,

    @Embedded
    val networkWeatherCondition: NetworkWeatherCondition
)
