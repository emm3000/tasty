package com.emm.data.di

import com.emm.data.source.datasource.PlaceApiDataSource
import com.emm.data.source.datasource.PlaceApiDataSourceImpl
import com.emm.data.source.datasource.RecipeDataSource
import com.emm.data.source.datasource.RecipeDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RecipeDataSource> { RecipeDataSourceImpl(get()) }
    single<PlaceApiDataSource> { PlaceApiDataSourceImpl(get()) }
}