package com.example.weatherpk.ui.registration

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.weatherpk.MyApp
import com.example.weatherpk.database.User
import io.realm.Realm
import org.kodein.di.generic.instance

@InjectViewState
class RegistrationActivityMvpPresenter : MvpPresenter<RegistrationActivityMvpView>() {

    private var realm: Realm = Realm.getDefaultInstance()
    private lateinit var user: User
    private val pref: SharedPreferences by MyApp.kodein.instance()
    private val APP_PREFERENCES_USER = "user"

    fun registerUser(login: String, password: String) {
        if (login == null || password == null){
            viewState.showError("Input all information!")
        } else if (findUser(login)){
            viewState.showError("User with this login is already exist!")
        } else {
            addUser(login, password)
            addInPreferences()
            viewState.startNewActivity()
        }
    }

    private fun addInPreferences() {
        pref.edit().putBoolean(APP_PREFERENCES_USER, true).apply()
    }

    private fun findUser(login: String) : Boolean {
        user = realm.where(User::class.java).equalTo("login", login).findFirst() ?: return false
        return true
    }

    private fun addUser(login: String, password: String) {
        var id = 0
        if (realm.where(User::class.java).max("userId")?.toInt() != null) {
            id = realm.where(User::class.java).max("userId")!!.toInt() + 1
        }
        user = User(id, login, password)
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(user)
            realm.commitTransaction()
        } catch (e: Exception) {
            println(e)
        }
    }
}