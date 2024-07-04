package com.example.testovoeplanetickets.models

import androidx.compose.ui.layout.IntrinsicMeasurable

data class OffersTicketsResponse(
    val tickets_offers: List<OffersTicketsModel>
)

data class OffersTicketsModel(
    val id : Int,
    val title : String,
    val time_range : List<String>,
    val price : Price
)
