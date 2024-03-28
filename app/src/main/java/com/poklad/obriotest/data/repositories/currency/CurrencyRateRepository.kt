package com.poklad.obriotest.data.repositories.currency

import com.poklad.obriotest.data.common.models.CurrencyDataModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRateRepository {
    suspend fun observeCurrencyBTCtoUSD(): Flow<CurrencyDataModel?>
    suspend fun getLastUpdateTime(): Long?
    suspend fun refreshCurrencyBTC(): Boolean
}