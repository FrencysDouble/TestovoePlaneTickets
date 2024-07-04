package com.example.testovoeplanetickets.models

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SearchResultModel(
    val id : Int,
    val price : Int,
    val departureAir : String,
    val departureTime : String,
    val arrivalAir : String,
    val arrivalTime : String,
    val timeInRoad : String,
    val has_transfer : String,
    val badge : String?

)
{
    companion object
    {
        fun map(ato : TicketsResponse): MutableList<SearchResultModel> {
            val mappedData = emptyList<SearchResultModel>().toMutableList()



            for (to in ato.tickets)
            {
                val departureDate = toDate(to.departure.date)
                val arrivalDate = toDate(to.arrival.date)

                val timeInRoadMillis = kotlin.math.abs(arrivalDate.time - departureDate.time)
                val timeInRoadFormatted = formatMillisToHoursMinutes(timeInRoadMillis)
                val transferText = when(to.has_transfer) {
                    true -> "/ С пересадками"
                    false -> "/ Без пересадок"
                }
                mappedData.add(
                    SearchResultModel(
                        id = to.id,
                        price = to.price?.value ?: 1000,
                        departureAir = to.departure.airport,
                        arrivalAir = to.arrival.airport,
                        departureTime = to.departure.date.substring(11,16),
                        arrivalTime = to.arrival.date.substring(11,16),
                        timeInRoad = timeInRoadFormatted + "ч в пути ",
                        has_transfer = transferText,
                        badge = to.badge
                    )
                )
            }
            return mappedData
        }

        private fun toDate(dateTimeString: String): Date {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            return format.parse(dateTimeString) ?: Date()
        }

        private fun formatMillisToHoursMinutes(millis: Long): String {
            val hours = millis / (1000 * 60 * 60)
            val minutes = (millis % (1000 * 60 * 60)) / (1000 * 60)
            return String.format("%02d:%02d", hours, minutes)
        }
    }
}
