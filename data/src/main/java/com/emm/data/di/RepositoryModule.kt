package com.emm.data.di

import com.emm.data.repository.PlaceApiRepositoryImpl
import com.emm.data.repository.RecipeRepositoryImpl
import com.emm.domain.repository.PlaceApiRepository
import com.emm.domain.repository.RecipeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single<PlaceApiRepository> { PlaceApiRepositoryImpl(get()) }
}