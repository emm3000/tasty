package com.emm.domain.di

import com.emm.domain.usecases.GetMarkersUseCase
import com.emm.domain.usecases.GetRecipeListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetRecipeListUseCase(get()) }
    factory { GetMarkersUseCase(get()) }

}