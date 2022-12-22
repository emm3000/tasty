package com.emm.tasty.di

import com.emm.tasty.activity.MainViewModel
import com.emm.tasty.fragments.detail.DetailViewModel
import com.emm.tasty.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { parameters -> DetailViewModel(parameters[0]) }
}