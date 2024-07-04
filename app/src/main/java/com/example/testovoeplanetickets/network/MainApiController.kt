package com.example.testovoeplanetickets.network

import android.app.appsearch.SearchResult
import android.util.Log
import com.example.testovoeplanetickets.R
import com.example.testovoeplanetickets.di.ApiModule
import com.example.testovoeplanetickets.models.ImageModel
import com.example.testovoeplanetickets.models.PlaneScreenModel
import com.example.testovoeplanetickets.models.SearchResultModel
import com.example.testovoeplanetickets.models.SearchScreenModel
import com.example.testovoeplanetickets.models.TicketModel
import com.example.testovoeplanetickets.network.api.ApiResponse
import com.example.testovoeplanetickets.network.api.MainMenuApi
import com.example.testovoeplanetickets.network.api.handleApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException

class MainApiController(private val apiModule: ApiModule) : MainMenuApiInterface {


    private val mainMenuApi: MainMenuApi
        get() = apiModule.provideMainMenuApi()


    override suspend fun getMainMenuData() : Flow<ApiResponse<List<PlaneScreenModel>>> =
        flow {
            val response = handleApiResponse(
                call = { mainMenuApi.getMainMenuData() },
                map = { PlaneScreenModel.map(it) })
            emit(response)
        }

    override suspend fun getSearchScreenData(): Flow<ApiResponse<List<SearchScreenModel>>> =
        flow {
            val response = handleApiResponse(
                call = {mainMenuApi.getSearchData()},
                map = {SearchScreenModel.map(it)}
            )
            emit(response)
        }

    override suspend fun getAllTicketsData(): Flow<ApiResponse<List<SearchResultModel>>> =
        flow {
            try {
                val response = handleApiResponse(
                    call = { mainMenuApi.getAllTickets() },
                    map = {SearchResultModel.map(it)}
                )
                emit(response)
            }
            catch(e : NullPointerException)
            {

                emit(ApiResponse.Error(e.message ?: "Null Pointer"))

            }
        }


}


interface MainMenuApiInterface
{
    suspend fun getMainMenuData() : Flow<ApiResponse<List<PlaneScreenModel>>>

    suspend fun getSearchScreenData() : Flow<ApiResponse<List<SearchScreenModel>>>

    suspend fun getAllTicketsData() : Flow<ApiResponse<List<SearchResultModel>>>
}