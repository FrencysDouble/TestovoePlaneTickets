package com.example.testovoeplanetickets.models


data class OffersResponse(
    val offers: List<OffersModel>
)

data class OffersModel(
    val id : Int,
    val title : String,
    val town : String,
    val price : Price
)

data class Price(
    val value : Int
)
