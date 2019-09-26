package com.example.weatherpk.ui.main

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.weatherpk.MyApp
import com.example.weatherpk.R
import com.example.weatherpk.internet.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.generic.instance

@InjectViewState
class MainActivityMvpPresenter : MvpPresenter<MainActivityMvpView>() {

    private var disposable: Disposable? = null
    private val weatherService: WeatherRepository by MyApp.kodein.instance()
    private val pref: SharedPreferences by MyApp.kodein.instance()
    private val APP_PREFERENCES_USER = "user"

    fun searchByCity(queryCity: String) {
        disposable = weatherService.getWeatherByCity(queryCity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> viewState.showWeatherForecast(result.main.temp.toInt() - 273,
                                                                result.main.pressure,
                                                                result.wind.speed)},
                { error -> viewState.showError(error.message) }
            )
    }

    fun searchByCoordinates(lat: String, lon: String) {
        disposable = weatherService.getWeatherByCoordinates(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> viewState.showWeatherForecast(result.main.temp.toInt() - 273,
                                                                result.main.pressure,
                                                                result.wind.speed)},
                { error -> viewState.showError(error.message) }
            )
    }

    fun logOut() {
        pref.edit().putBoolean(APP_PREFERENCES_USER, false).apply()
        viewState.returnRegistrationActivity()
    }

    fun onPause() {
        disposable?.dispose()
    }
}