package com.example.testovoeplanetickets.controllers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testovoeplanetickets.cache.CacheDataManager
import java.lang.NullPointerException

class SelectedText(cacheDataManager: CacheDataManager) : ViewModel() {


    private val _selectedDestination = MutableLiveData<String>()
    val selectedDestination: LiveData<String> = _selectedDestination

    private val _selectedApproach = MutableLiveData<String>()
    val selectedApproach: LiveData<String> = _selectedApproach

    init {
        try {
            val cachedData = cacheDataManager.getData("search_bar_1")
            _selectedDestination.value = cachedData
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDestination(text : String)
    {
        _selectedDestination.value = text
    }

    fun setApproach(text: String)
    {
        _selectedApproach.value = text
    }


    fun shuffleText()
    {
        val app = _selectedApproach.value
        val dest = _selectedDestination.value
        _selectedApproach.value = dest
        _selectedDestination.value = app
    }
}