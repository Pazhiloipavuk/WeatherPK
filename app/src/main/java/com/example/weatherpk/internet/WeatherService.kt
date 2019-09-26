package com.example.weatherpk.internet

import com.example.weatherpk.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather?APPID=39b26bdba8d1595289b541b253197efc")
    fun getWeatherByCity(@Query("q") queryCity: String): Observable<WeatherResponse.Result>

    @GET("data/2.5/weather?APPID=39b26bdba8d1595289b541b253197efc")
    fun getWeatherByCoordinates(@Query("lat") lat: String, @Query("lon") lon: String): Observable<WeatherResponse.Result>
}
