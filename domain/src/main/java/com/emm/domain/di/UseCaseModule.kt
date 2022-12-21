package com.emm.domain.di

import com.emm.domain.usecases.GetUserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetUserUseCase(get()) }

}