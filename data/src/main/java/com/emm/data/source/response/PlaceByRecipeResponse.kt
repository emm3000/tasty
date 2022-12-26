package com.emm.data.source.response


import com.emm.domain.entitys.PlaceEntity
import com.google.gson.annotations.SerializedName

data class PlaceByRecipeResponse(
    val results: List<PlaceItemResponse>,
    val status: String
)

data class PlaceItemResponse(
    val geometry: GeometryResponse,
    val icon: String,
    val name: String,
    val photos: List<PhotoResponse>?,
    @SerializedName("place_id")
    val placeId: String,
    val rating: Double,
    val vicinity: String
)

data class GeometryResponse(
    val location: LocationResponse,
)

data class LocationResponse(
    val lat: Double,
    val lng: Double
)

data class PhotoResponse(
    @SerializedName("photo_reference")
    val photoReference: String,
)

fun List<PlaceItemResponse>.toDomainEntity(): List<PlaceEntity> {
    return map(PlaceItemResponse::toDomainEntity)
}

private fun PlaceItemResponse.toDomainEntity(): PlaceEntity {
    return PlaceEntity(
        latitude = geometry.location.lat,
        longitude = geometry.location.lng,
        photoReference = photos?.firstOrNull()?.photoReference,
        name = name,
        street = vicinity,
        placeId = placeId
    )
}