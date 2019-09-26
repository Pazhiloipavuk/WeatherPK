package com.example.weatherpk.internet

import com.example.weatherpk.model.WeatherResponse
import io.reactivex.Observable

class WeatherRepository (private val service: WeatherService) {
    fun getWeatherByCity(city: String) : Observable<WeatherResponse.Result> {
        return service.getWeatherByCity(city)
    }

    fun getWeatherByCoordinates (lat: String, lon: String) : Observable<WeatherResponse.Result> {
        return service.getWeatherByCoordinates(lat, lon)
    }
}