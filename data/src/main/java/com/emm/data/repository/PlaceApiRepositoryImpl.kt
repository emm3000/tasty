package com.emm.data.repository

import com.emm.data.source.datasource.PlaceApiDataSource
import com.emm.data.source.response.toDomainEntity
import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.repository.PlaceApiRepository
import com.emm.domain.utils.ResultState
import com.emm.domain.utils.buildFlowWithResultState
import kotlinx.coroutines.flow.Flow

class PlaceApiRepositoryImpl(
    private val placeApiDataSource: PlaceApiDataSource
) : PlaceApiRepository {

    override fun getPlaceList(queries: Map<String, String>): Flow<ResultState<List<PlaceEntity>>> {
        return buildFlowWithResultState(
            fetchData = { placeApiDataSource.getMarkersByRecipe(queries) },
            mapToDomainModel = { it.results.toDomainEntity() }
        )
    }

}