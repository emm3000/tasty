package com.emm.tasty.di

import com.emm.tasty.fragments.detail.DetailViewModel
import com.emm.tasty.fragments.home.HomeViewModel
import com.emm.tasty.fragments.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { parameters -> DetailViewModel(parameters[0]) }
    viewModel { parameters -> MapViewModel(get(), parameters[0]) }
}