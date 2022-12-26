package com.emm.data.source.datasource

import com.emm.data.source.api.RestClient
import com.emm.data.source.response.PlaceByRecipeResponse
import com.emm.data.utils.safeApiCall
import com.emm.domain.utils.ResultState

class PlaceApiDataSourceImpl(
    private val restClient: RestClient
): PlaceApiDataSource {

    override suspend fun getMarkersByRecipe(query: Map<String, String>): ResultState<PlaceByRecipeResponse> {
        return safeApiCall { restClient.getMarkersByRecipe(query) }
    }

}