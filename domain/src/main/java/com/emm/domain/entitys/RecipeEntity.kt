package com.emm.domain.entitys

data class RecipeEntity(
    val id: Int,
    val ingredients: List<String>,
    val latitude: String,
    val longitude: String,
    val portions: String,
    val preparation: List<String>,
    val time: String,
    val title: String,
    val urlImage: String
)
