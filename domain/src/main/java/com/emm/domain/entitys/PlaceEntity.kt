package com.emm.domain.entitys

data class PlaceEntity(
    val latitude: Double,
    val longitude: Double,
    val photoReference: String?,
    val name: String,
    val street: String,
    val placeId: String
)