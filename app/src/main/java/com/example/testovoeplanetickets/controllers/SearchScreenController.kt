package com.example.testovoeplanetickets.controllers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testovoeplanetickets.models.PlaneScreenModel
import com.example.testovoeplanetickets.models.SearchResultModel
import com.example.testovoeplanetickets.models.SearchScreenModel
import com.example.testovoeplanetickets.models.TicketModel
import com.example.testovoeplanetickets.network.MainMenuApiInterface
import com.example.testovoeplanetickets.network.api.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class SearchScreenController(private val mainMenuApiInterface: MainMenuApiInterface) : ViewModel() {

    val liveSearchData: MutableLiveData<List<SearchScreenModel>> = MutableLiveData(emptyList())

    val liveSearchAllData: MutableLiveData<List<SearchResultModel>> = MutableLiveData(emptyList())


    fun dataLoad() {
        viewModelScope.launch {
            mainMenuApiInterface.getSearchScreenData().collect { response ->
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            liveSearchData.value = response.data
                        }

                        is ApiResponse.Error -> {
                            Log.e("SearchScreenController", "Error: ${response.errorMessage}")
                        }

                        is ApiResponse.Loading -> {
                            Log.d("SearchScreenController", "Loading...")
                        }
                    }
                }
            }
        }
    }

    fun dataSearchLoad() {
        viewModelScope.launch {
            mainMenuApiInterface.getAllTicketsData().collect { response ->
                withContext(Dispatchers.Main)
                {
                    when (response) {
                        is ApiResponse.Success -> {
                            liveSearchAllData.value = response.data
                        }

                        is ApiResponse.Error -> {
                            Log.e("SearchScreenController", "Error: ${response.errorMessage}")
                        }

                        is ApiResponse.Loading -> {
                            Log.d("SearchScreenController", "Loading...")
                        }
                    }
                }
            }
        }
    }

    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMM", Locale("ru"))
        return currentDate.format(formatter)
    }
}