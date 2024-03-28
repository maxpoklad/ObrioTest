package com.poklad.obriotest.domain.usecases

import com.poklad.obriotest.presentation.model.CurrencyPresentationModel
import com.poklad.obriotest.data.repositories.currency.CurrencyRateRepository
import com.poklad.obriotest.domain.mapper.CurrencyDomainMapper
import com.poklad.obriotest.utils.connectivity.ConnectivityChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyMultipleUseCases @Inject constructor(
    private val repository: CurrencyRateRepository,
    private val mapper: CurrencyDomainMapper,
    private val connectivityChecker: ConnectivityChecker
) {

    suspend fun observeCurrencyBTCtoUSD(): Flow<CurrencyPresentationModel> =
        repository.observeCurrencyBTCtoUSD().filterNotNull().map(mapper::map)

    suspend fun refreshCurrencyBTC(): Boolean = if (connectivityChecker.isConnected()) {
        repository.refreshCurrencyBTC()
    } else {
        false
    }

    suspend fun getTimeFromLastUpdate(): Long = System.currentTimeMillis() - (repository.getLastUpdateTime() ?: 0)

}