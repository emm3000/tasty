package com.emm.domain.constants

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object Constants : KoinComponent {

    private val keyMap: String by inject()

    val PLACE_QUERIES = mapOf(
        "location" to "d",
        "radius" to "1500",
        "type" to "restaurant",
        "key" to "AIzaSyBPIDN1UJy71dSA4u8Y-Oczg-c3KHiFxp4",
        "language" to "es"
    )

    const val LOCATION_KEY = "location"
    const val KEYWORD_KEY = "keyword"

}