package com.example.testovoeplanetickets.models


data class TicketsResponse(
    val tickets : List<TicketModel>
)

data class TicketModel(
    val id : Int,
    val badge : String?,
    val price : Price?,
    val provider_name : String,
    val company : String,
    val departure : Departure,
    val arrival : Arrival,
    val has_transfer : Boolean,
    val has_visa_transfer : Boolean,
    val luggage : Luggage,
    val hand_luggage: Hand_Luggage,
    val is_returnable : Boolean,
    val is_exchangable : Boolean
)

data class Departure(
    val town : String,
    val date : String,
    val airport : String
)

data class Arrival(
    val town : String,
    val date : String,
    val airport : String
)

data class Luggage(
    val has_luggage : Boolean,
    val price : Price?
)

data class Hand_Luggage(
    val has_hand_luggage : Boolean,
    val size : String?
)

