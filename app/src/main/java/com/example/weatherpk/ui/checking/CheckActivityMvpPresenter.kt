package com.example.weatherpk.ui.checking

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.weatherpk.MyApp
import org.kodein.di.generic.instance

@InjectViewState
class CheckActivityMvpPresenter : MvpPresenter<CheckActivityMvpView>() {

    private val pref: SharedPreferences by MyApp.kodein.instance()
    private val APP_PREFERENCES_USER = "user"

    fun checkRegistration() {
        if (pref.contains(APP_PREFERENCES_USER)) {
            if(pref.getBoolean(APP_PREFERENCES_USER, false)) viewState.startActivity(1)
            else viewState.startActivity(0)
        } else viewState.startActivity(0)
    }
}