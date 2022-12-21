package com.emm.domain.repository

import com.emm.domain.entitys.UserModel
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<ResultState<UserModel>>

}