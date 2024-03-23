package com.poklad.obriotest.data.remote.data_source

import com.poklad.obriotest.data.common.data_sources.CurrencyDataSource
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel
import com.poklad.obriotest.data.remote.CurrencyApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class RemoteCurrencyDataSource @Inject constructor(private val api: CurrencyApi) : CurrencyDataSource {
    override suspend fun getCurrencyInfo(currency: Currencies, coins: Coins): CurrencyDataModel = api.getCurrencyUSDInfo().bpi.usd.convertToCurrencyDataModel(Coins.BTC)
}