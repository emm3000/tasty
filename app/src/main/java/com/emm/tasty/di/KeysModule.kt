package com.emm.tasty.di

import com.emm.tasty.R
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val keysModule = module {

    single { androidContext().getString(R.string.PLACE_API_KEY) }

}
