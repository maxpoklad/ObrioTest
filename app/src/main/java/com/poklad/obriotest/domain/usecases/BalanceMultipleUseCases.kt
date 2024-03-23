package com.poklad.obriotest.domain.usecases

import com.poklad.obriotest.data.repositories.balance.WalletRepository
import com.poklad.obriotest.domain.mapper.BalanceDomainMapper
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BalanceMultipleUseCases @Inject constructor(private val repository: WalletRepository, private val mapper: BalanceDomainMapper) {

    suspend fun observeBalanceBTC() = repository.observeBalanceBTC().filterNotNull().map(mapper::map)

    suspend fun depositBTC(amount: Float) = repository.depositCoinsBTC(amount)

}