package com.example.testovoeplanetickets.controllers

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testovoeplanetickets.cache.CacheDataManager
import com.example.testovoeplanetickets.models.PlaneScreenModel
import com.example.testovoeplanetickets.network.MainMenuApiInterface
import com.example.testovoeplanetickets.network.api.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaneScreenController(
    private val cacheDataManager: CacheDataManager,
    private val mainMenuApiInterface: MainMenuApiInterface) : ViewModel() {

    val isDialogShow : MutableState<Boolean> = mutableStateOf(false)

    val livePlaneData: MutableLiveData<List<PlaneScreenModel>> = MutableLiveData(emptyList())



    fun dataLoad()
    {
        viewModelScope.launch {
            mainMenuApiInterface.getMainMenuData().collect {response ->
                withContext(Dispatchers.Main) {
                    when(response) {
                        is ApiResponse.Success -> {
                            livePlaneData.value = response.data
                        }
                        is ApiResponse.Error -> {
                            Log.e("PlaneController", "Error: ${response.errorMessage}")
                        }
                        is ApiResponse.Loading -> {
                            Log.d("PlaneController", "Loading...")
                        }

                    }
                }
            }
        }
    }


    fun saveCacheData(text : String)
    {
        cacheDataManager.saveData("search_bar_1",text)
    }

    fun getCacheData() : String
    {
        return cacheDataManager.getData("search_bar_1")
    }



    fun dialogActive()
    {
        isDialogShow.value = true
    }

    fun dialogDisable()
    {
        isDialogShow.value = false
    }

}