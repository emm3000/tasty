package com.emm.tasty.models

import com.emm.domain.entitys.PlaceEntity

data class PlaceModel(
    val latitude: Double,
    val longitude: Double,
    val photoReference: String?,
    val name: String,
    val street: String,
    val placeId: String
)

fun List<PlaceEntity>.toUIModel(): List<PlaceModel> {
    return map(PlaceEntity::toUIModel)
}

private fun PlaceEntity.toUIModel(): PlaceModel {
    return PlaceModel(
        latitude = latitude,
        longitude = longitude,
        photoReference = photoReference,
        name = name,
        street = street,
        placeId = placeId
    )
}