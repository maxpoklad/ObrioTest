package com.poklad.obriotest.presentation.model

data class CurrencyPresentationModel(
    val rate: Float = 0f,
    val currency: String = "",
    val coin: String = ""
)