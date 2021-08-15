package com.udaychugh.weatherapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udaychugh.weatherapp.data.model.NetworkWeatherCondition
import com.udaychugh.weatherapp.data.model.NetworkWeatherDescription
import com.udaychugh.weatherapp.data.model.Wind

// This class represents the Database DTO
@Entity(tableName = "weather_table")
data class DBWeather(

    @ColumnInfo(name = "unique_id")
    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0,

    @ColumnInfo(name = "city_id")
    val cityId: Int,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @Embedded
    val wind: Wind,

    @ColumnInfo(name = "weather_details")
    val networkWeatherDescription: List<NetworkWeatherDescription>,

    @Embedded
    val networkWeatherCondition: NetworkWeatherCondition
)
