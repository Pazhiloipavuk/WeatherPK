package com.example.weatherpk.ui.main

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.weatherpk.R
import com.example.weatherpk.ui.MvpAppCompatActivity
import com.example.weatherpk.ui.registration.RegistrationActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.example.weatherpk.ui.adapter.ListAdapter as myListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  MvpAppCompatActivity(), MainActivityMvpView {

    private var fusedLocationClient: FusedLocationProviderClient? = null

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mainActivityMvpPresenter: MainActivityMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAllListenerButtons()

        connectRecyclerView()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> mainActivityMvpPresenter.logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun returnRegistrationActivity() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun cityClicked(city : String) {
        mainActivityMvpPresenter.searchByCity(city)
    }

    private fun initAllListenerButtons() {
        btnSearchByLocation.setOnClickListener {
            searchByCoordinates()
        }

        autoCompleteTextView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                mainActivityMvpPresenter.searchByCity(autoCompleteTextView.text.toString())
                return@OnKeyListener true
            }
            false
        })
    }

    private fun searchByCoordinates() {
        if (checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
            fusedLocationClient?.lastLocation?.
                addOnSuccessListener(this){location : Location? ->
                    if(location != null) {
                        mainActivityMvpPresenter.searchByCoordinates(
                            location.latitude.toString(),
                            location.longitude.toString())
                    } else {
                        showError("Turn on Internet")
                    }
                }
        }
    }

    private fun checkPermission(vararg perm:String) : Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(this,it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if(perm.toList().any {
                    ActivityCompat.
                        shouldShowRequestPermissionRationale(this, it)}
            ) {
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Permission")
                    .setMessage("Permission needed!")
                    .setPositiveButton("OK"){_, _ ->
                        ActivityCompat.requestPermissions(this, perm,
                            PERMISSION_ID
                        )
                    }
                    .setNegativeButton("No"){_, _ -> }
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(this, perm,
                    PERMISSION_ID
                )
            }
            return false
        }
        return true
    }

    override fun showWeatherForecast(temp: Int, pressure: Int, windSpeed: Double) {
        txt_temp.text = "Temperature:$temp"
        txt_pressure.text = "Pressure:$pressure"
        txt_wind.text = "Wind speed:$windSpeed"
    }

    private fun getAllCities() : ArrayList<String> = resources.
                                                     getStringArray(R.array.cities_array).
                                                     toCollection(ArrayList())

    private fun connectRecyclerView() {
        vRvCities.layoutManager = LinearLayoutManager(this)
        vRvCities.adapter = myListAdapter(getAllCities()) { city: String -> cityClicked(city) }
    }

    override fun showError(error: String?) = Toast.makeText(this, error, Toast.LENGTH_LONG)
                                                  .show()

    override fun onPause() {
        super.onPause()
        mainActivityMvpPresenter.onPause()
    }

    companion object {
        const val PERMISSION_ID = 42
    }
}
