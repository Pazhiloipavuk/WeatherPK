package com.example.weatherpk.ui.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.weatherpk.R
import com.example.weatherpk.ui.MvpAppCompatActivity
import com.example.weatherpk.ui.checking.CheckActivity
import com.example.weatherpk.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : MvpAppCompatActivity(), RegistrationActivityMvpView {

    @InjectPresenter
    lateinit var registrationActivityMvpPresenter: RegistrationActivityMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initFinishListener()
    }

    private fun initFinishListener() {
        finish_registration.setOnClickListener {
            registrationActivityMvpPresenter.registerUser(login_new.text.toString(),
                                                          password_new.text.toString())
        }
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun startNewActivity() {
        val newIntent = Intent(this, MainActivity::class.java)
        startActivity(newIntent)
    }
}