package com.example.weatherpk.model

object WeatherResponse {
    data class Result(val main: Main, val wind: Wind)
    data class Main(val temp: Double, val pressure: Int)
    data class Wind(val speed: Double)
}