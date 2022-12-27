package com.emm.tasty.fragments.fakerepository

import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.repository.PlaceApiRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakePlaceApiRepository : PlaceApiRepository {

    private val flow = MutableSharedFlow<ResultState<List<PlaceEntity>>>()

    suspend fun emit(value: List<PlaceEntity>) = flow.emit(ResultState.Success(value))

    override fun getPlaceList(queries: Map<String, String>): Flow<ResultState<List<PlaceEntity>>> {
        return flow
    }

}