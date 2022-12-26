package com.emm.domain.fakerepositories

import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.fakedata.FakeResponse.FAKE_PLACES_LIST
import com.emm.domain.repository.PlaceApiRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePlaceApiRepository : PlaceApiRepository {

    override fun getPlaceList(queries: Map<String, String>): Flow<ResultState<List<PlaceEntity>>> {
        return flow {
            emit(
                ResultState.Success(
                    FAKE_PLACES_LIST
                )
            )
        }
    }

}