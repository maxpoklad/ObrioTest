package com.poklad.obriotest.data.repositories.balance

import androidx.paging.PagingData
import com.poklad.obriotest.data.common.models.BalanceDataModel
import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.common.models.TransactionDestinations
import kotlinx.coroutines.flow.Flow

interface WalletRepository {

    suspend fun observeBalanceBTC(): Flow<BalanceDataModel?>

    suspend fun getTransactionHistory(limit: Int): Flow<PagingData<TransactionDataModel>>

    suspend fun depositCoinsBTC(amount: Float): Boolean

    suspend fun makeTransactionBTC(amount: Float, destination: TransactionDestinations): Boolean

    fun getPossibleDestinations(): List<String>

}