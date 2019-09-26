package com.example.weatherpk.ui.main

import com.arellomobile.mvp.MvpView

interface MainActivityMvpView : MvpView {
    fun showWeatherForecast(temp: Int, pressure: Int, windSpeed: Double)
    fun showError(error: String?)
    fun returnRegistrationActivity()
}