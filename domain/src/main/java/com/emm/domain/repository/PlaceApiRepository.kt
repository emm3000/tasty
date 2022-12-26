package com.emm.domain.repository

import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface PlaceApiRepository {

    fun getPlaceList(
        queries: Map<String, String>
    ): Flow<ResultState<List<PlaceEntity>>>

}