package com.example.weatherpk

import android.app.Application
import android.content.SharedPreferences
import com.example.weatherpk.internet.ApiRest
import com.example.weatherpk.internet.WeatherRepository
import com.example.weatherpk.internet.WeatherService
import com.example.weatherpk.util.Preferences
import io.realm.Realm
import io.realm.RealmConfiguration
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

class MyApp : Application() {
    companion object {
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            bind<Retrofit>() with singleton {
                ApiRest.getApi()
            }

            bind<WeatherRepository>() with singleton {
                WeatherRepository(instance<Retrofit>().create(WeatherService::class.java))
            }

            bind<SharedPreferences>() with singleton {
                Preferences.getPreferences(applicationContext)
            }
        }
        initRealm()
    }

    private fun initRealm(){
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}