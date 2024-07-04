package com.example.testovoeplanetickets.cache

import android.content.SharedPreferences
import android.util.Log

class CacheDataManager(private val sharedPreferences: SharedPreferences) {



    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String): String {
        val defaultValue = ""
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}