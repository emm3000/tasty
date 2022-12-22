package com.emm.data.repository

import com.emm.data.source.datasource.UserDataSource
import com.emm.data.source.response.toDomainModel
import com.emm.domain.entitys.UserEntity
import com.emm.domain.repository.UserRepository
import com.emm.domain.utils.ResultState
import com.emm.domain.utils.buildFlowWithResultState
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun getUser(): Flow<ResultState<UserEntity>> {
        return buildFlowWithResultState(
            fetchData = { userDataSource.getUser() },
            mapToDomainModel = { it.toDomainModel() }
        )
    }
}

