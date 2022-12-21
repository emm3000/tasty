package com.emm.domain.usecases

import com.emm.domain.entitys.UserModel
import com.emm.domain.repository.UserRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<ResultState<UserModel>> {
        return userRepository.getUser()
    }

}