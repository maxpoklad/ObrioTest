package com.poklad.obriotest.data.repositories.currency

import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel
import com.poklad.obriotest.data.local.data_source.CacheCurrencyDataSource
import com.poklad.obriotest.data.remote.data_source.RemoteCurrencyDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultCurrencyRateRepository @Inject constructor(
    private val cacheDataSource: CacheCurrencyDataSource,
    private val remoteDataSource: RemoteCurrencyDataSource,
) : CurrencyRateRepository {

    override suspend fun observeCurrencyBTCtoUSD(): Flow<CurrencyDataModel?> =
        cacheDataSource.observerCurrencyInfo(Currencies.USD, Coins.BTC)

    override suspend fun getLastUpdateTime(): Long? = cacheDataSource.getCurrencyInfo(Currencies.USD, Coins.BTC)?.lastUpdateTime

    override suspend fun refreshCurrencyBTC(): Boolean = remoteDataSource.getCurrencyInfo(Currencies.USD, Coins.BTC)?.let {
        cacheDataSource.updateCurrencyInfo(it.rate, it.currency, it.coins, System.currentTimeMillis())
        true
    } ?: false
}