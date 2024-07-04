package com.example.testovoeplanetickets.models

import android.media.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.testovoeplanetickets.R

data class PlaneScreenModel(
    val id : Int,
    val title : String,
    val town : String,
    val price : Int,
    val imageId : Int
)
{

    companion object {
        fun map(oml : OffersResponse): MutableList<PlaneScreenModel> {
            val mappedData = emptyList<PlaneScreenModel>().toMutableList()
            for (om in oml.offers)
            {
                mappedData.add(
                    PlaneScreenModel(
                        id = om.id,
                        title = om.title,
                        town = om.town,
                        price = om.price.value,
                        imageId = when (om.id) {
                            1 -> R.drawable.artist_photo_1
                            2 -> R.drawable.artist_photo_2
                            3 -> R.drawable.artist_photo_3
                            else -> R.drawable.artist_photo_1
                        }
                    )
                )

            }
            return mappedData

        }
    }
}
