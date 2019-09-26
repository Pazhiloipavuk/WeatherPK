package com.example.weatherpk.ui.registration

import com.arellomobile.mvp.MvpView

interface RegistrationActivityMvpView : MvpView {
    fun showError(error: String)
    fun startNewActivity()
}