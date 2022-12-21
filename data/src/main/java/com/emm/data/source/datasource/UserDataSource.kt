package com.emm.data.source.datasource

import com.emm.data.source.response.UserResponse
import com.emm.domain.utils.ResultState

interface UserDataSource {

    suspend fun getUser(): ResultState<UserResponse>

}