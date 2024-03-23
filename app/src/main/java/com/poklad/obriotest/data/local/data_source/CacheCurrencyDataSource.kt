package com.poklad.obriotest.data.local.data_source

import com.poklad.obriotest.data.common.data_sources.CurrencyDataSource
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel
import com.poklad.obriotest.data.local.dao.CurrencyDao
import com.poklad.obriotest.data.local.models.CurrencyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheCurrencyDataSource @Inject constructor(private val currencyDao: CurrencyDao) : CurrencyDataSource {

    override suspend fun getCurrencyInfo(currency: Currencies, coins: Coins): CurrencyDataModel = currencyDao.getCurrencyInfo(currency, coins)

    suspend fun observerCurrencyInfo(currency: Currencies, coins: Coins): Flow<CurrencyDataModel?> =
        currencyDao.observeCurrencyInfo(currency, coins)

    suspend fun updateCurrencyInfo(rate: Float, currency: Currencies, coins: Coins) = currencyDao.updateCurrencyInfo(
        CurrencyEntity(
            id = coins.name + currency.name,
            rate = rate,
            currency = currency,
            coins = coins
        )
    )

}