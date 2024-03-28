package com.poklad.obriotest.data.common.data_sources

import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel
import kotlinx.coroutines.flow.Flow

interface CurrencyDataSource {
    suspend fun getCurrencyInfo(currency: Currencies, coins: Coins): CurrencyDataModel?
}