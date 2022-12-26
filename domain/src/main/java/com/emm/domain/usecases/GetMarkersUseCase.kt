package com.emm.domain.usecases

import com.emm.domain.constants.Constants
import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.repository.PlaceApiRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

class GetMarkersUseCase(private val placeApiRepository: PlaceApiRepository) {

    operator fun invoke(keyword: String, defaultLocation: String): Flow<ResultState<List<PlaceEntity>>> {
        return placeApiRepository.getPlaceList(
            buildQueries(
                keyword = keyword.sanityKeyword(),
                defaultLocation = defaultLocation
            )
        )
    }

    private fun buildQueries(keyword: String, defaultLocation: String): Map<String, String> {
        return Constants.PLACE_QUERIES.toMutableMap().also {
            it[Constants.KEYWORD_KEY] = keyword
            it[Constants.LOCATION_KEY] = defaultLocation
        }
    }

    private fun String.sanityKeyword(): String {
        return this
            .replace("Receta de", "", true)
            .replace("Receta", "", true)
            .trim()
    }

}