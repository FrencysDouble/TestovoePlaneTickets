package com.example.testovoeplanetickets.models

import com.example.testovoeplanetickets.R

data class SearchScreenModel(
    val id : Int,
    val title : String,
    val time_range : List<String>,
    val price : Int,
    val imageId : Int
) {

    companion object {
        fun map(offt : OffersTicketsResponse): MutableList<SearchScreenModel> {
            val mappedData = emptyList<SearchScreenModel>().toMutableList()
            for (offt in offt.tickets_offers) {
                mappedData.add(
                    SearchScreenModel(
                        id = offt.id,
                        title = offt.title,
                        time_range = offt.time_range,
                        price = offt.price.value,
                        imageId = when(offt.id)
                        {
                            1 -> R.drawable.search_screen_list_rect_red
                            10 -> R.drawable.search_screen_list_rect_blue
                            30 -> R.drawable.search_screen_list_rect_white
                            else -> R.drawable.search_screen_list_rect_red
                        }
                    )
                )
            }

            return mappedData

        }
    }
}