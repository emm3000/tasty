package com.emm.data.di

import com.emm.data.source.datasource.RecipeDataSource
import com.emm.data.source.datasource.RecipeDataSourceImpl
import com.emm.data.source.datasource.UserDataSource
import com.emm.data.source.datasource.UserDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<RecipeDataSource> { RecipeDataSourceImpl(get()) }
}