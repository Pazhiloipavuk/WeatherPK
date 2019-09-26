package com.example.weatherpk.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(@PrimaryKey
                var userId: Int = 0,
                var login: String = "",
                var password: String = "") : RealmObject()