package com.emm.domain.entitys

data class RecipePaginationDataEntity(
    val data: List<RecipeEntity>,
    val total: Int,
    val page: Int,
    val limit: Int,
    val pages: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean
)

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
