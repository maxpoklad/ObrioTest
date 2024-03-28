package com.poklad.obriotest.data.common.models

interface CurrencyDataModel {
    val currency: Currencies
    val coins: Coins
    val rate: Float
    val lastUpdateTime: Long
}