package com.emm.domain.fakedata

import com.emm.domain.entitys.PlaceEntity

object FakeResponse {

    val FAKE_PLACES_LIST = listOf(
        PlaceEntity(
            latitude = 0.0,
            longitude = 0.0,
            photoReference = "",
            name = "Cevichería Barrio",
            street = "",
            placeId = ""
        )
    )

}