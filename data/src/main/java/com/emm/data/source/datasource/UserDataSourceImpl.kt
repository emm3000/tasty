package com.emm.data.source.datasource

import com.emm.data.source.api.RestClient
import com.emm.data.source.response.UserResponse
import com.emm.data.utils.safeApiCall
import com.emm.domain.utils.ResultState

class UserDataSourceImpl(
    private val restClient: RestClient
) : UserDataSource {

    override suspend fun getUser(): ResultState<UserResponse> {
        return safeApiCall { restClient.getUser() }
    }

}