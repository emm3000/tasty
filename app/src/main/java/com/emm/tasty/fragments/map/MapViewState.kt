package com.emm.tasty.fragments.map

import com.emm.tasty.models.PlaceModel

data class MapViewState(
    val places: List<PlaceModel>? = null,
    val showLoading: Boolean = false,
    val errorMessage: String? = null
)
