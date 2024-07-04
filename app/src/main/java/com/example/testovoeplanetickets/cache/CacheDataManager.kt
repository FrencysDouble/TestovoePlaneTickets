package com.example.testovoeplanetickets.cache

import android.content.SharedPreferences

class CacheDataManager(private val sharedPreferences: SharedPreferences) {

    init {
        if ( getData("search_bar_1").isBlank()) {
            saveData("search_bar_1", "")
        }
    }

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String): String {
        return sharedPreferences.getString(key, null).toString()
    }
}