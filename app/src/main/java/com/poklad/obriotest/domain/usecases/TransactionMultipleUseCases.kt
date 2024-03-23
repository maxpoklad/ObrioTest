package com.poklad.obriotest.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.poklad.obriotest.data.common.models.TransactionDestinations
import com.poklad.obriotest.data.repositories.balance.WalletRepository
import com.poklad.obriotest.domain.mapper.TransactionDomainMapper
import com.poklad.obriotest.presentation.model.TransactionPresentationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionMultipleUseCases @Inject constructor(private val repository: WalletRepository, private val mapper: TransactionDomainMapper) {

    suspend fun observeTransactionHistory(limit: Int): Flow<PagingData<TransactionPresentationModel>> =
        repository.getTransactionHistory(limit).filterNotNull().map { it.map(mapper::map) }

    suspend fun makeTransactionBTC(amount: Float, destination: String) =
        repository.makeTransactionBTC(amount, TransactionDestinations.valueOf(destination))

    fun getPossibleDestinations() = repository.getPossibleDestinations()
}