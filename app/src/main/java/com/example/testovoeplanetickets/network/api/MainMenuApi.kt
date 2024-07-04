package com.example.testovoeplanetickets.network.api

import com.example.testovoeplanetickets.models.OffersModel
import com.example.testovoeplanetickets.models.OffersResponse
import com.example.testovoeplanetickets.models.OffersTicketsModel
import com.example.testovoeplanetickets.models.OffersTicketsResponse
import com.example.testovoeplanetickets.models.TicketModel
import com.example.testovoeplanetickets.models.TicketsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

class MainMenuApi(private val retrofit: Retrofit) {

    private val service : MainMenuApiPoints = retrofit.create(MainMenuApiPoints::class.java)

    suspend fun getMainMenuData(): Response<OffersResponse> = service.getMainMenu()

    suspend fun getSearchData() : Response<OffersTicketsResponse> = service.getBottomDialogData()

    suspend fun getAllTickets() : Response<TicketsResponse> = service.getAllTicketsData()
}

interface MainMenuApiPoints
{

    @GET(ApiRoutes.MAIN_MENU)
    suspend fun getMainMenu() : Response<OffersResponse>

    @GET(ApiRoutes.SEARCH_COUNTRY)
    suspend fun getBottomDialogData() : Response<OffersTicketsResponse>

    @GET(ApiRoutes.SHOW_ALL)
    suspend fun getAllTicketsData() : Response<TicketsResponse>
}