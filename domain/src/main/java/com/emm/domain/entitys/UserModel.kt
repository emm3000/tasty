package com.emm.domain.entitys

data class UserModel(
    val completed: Boolean = false,
    val id: Int = 0,
    val title: String = "",
    val userId: Int = 0,
    val isLoading: Boolean = false
)
