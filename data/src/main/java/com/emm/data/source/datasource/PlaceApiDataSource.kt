package com.emm.data.source.datasource

import com.emm.data.source.response.PlaceByRecipeResponse
import com.emm.domain.utils.ResultState

interface PlaceApiDataSource {

    suspend fun getMarkersByRecipe(query: Map<String, String>): ResultState<PlaceByRecipeResponse>

}