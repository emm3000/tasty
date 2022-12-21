package com.emm.data.source.response

import com.emm.domain.entitys.UserModel

data class UserResponse(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)

fun UserResponse.toDomainModel(): UserModel {
    return UserModel(
        completed = completed,
        id = id,
        title = title,
        userId = userId
    )
}