package com.example.weatherpk.ui.checking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.weatherpk.R
import com.example.weatherpk.ui.MvpAppCompatActivity
import com.example.weatherpk.ui.main.MainActivity
import com.example.weatherpk.ui.registration.RegistrationActivity

class CheckActivity : MvpAppCompatActivity(), CheckActivityMvpView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var checkActivityMvpPresenter: CheckActivityMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        checkActivityMvpPresenter.checkRegistration()
    }

    override fun startActivity(activity: Int) {
        lateinit var intent: Intent
        when (activity) {
            0 -> intent = Intent(this, RegistrationActivity::class.java)
            1 -> intent = Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
    }
}
