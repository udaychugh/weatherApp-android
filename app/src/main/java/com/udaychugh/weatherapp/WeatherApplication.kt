package com.udaychugh.weatherapp

import android.app.Application

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    val weatherRepository: WeatherRepository
    get() = ServiceLocator.provideWeatherRepository(this)
}