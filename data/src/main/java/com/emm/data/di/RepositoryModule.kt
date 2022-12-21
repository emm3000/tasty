package com.emm.data.di

import com.emm.data.repository.UserRepositoryImpl
import com.emm.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}