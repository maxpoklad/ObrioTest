package com.poklad.obriotest.data.common.data_sources

import androidx.paging.PagingData
import com.poklad.obriotest.data.common.models.BalanceDataModel
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.common.models.TransactionDestinations
import kotlinx.coroutines.flow.Flow

interface WalletDataSource {

    suspend fun getCurrentBalance(coin: Coins): Flow<BalanceDataModel?>

    suspend fun getTransactionsHistory(limit: Int): Flow<PagingData<TransactionDataModel>>

    suspend fun makeTranslation(amount: Float, coin: Coins, transactionDestinations: TransactionDestinations): Boolean
}