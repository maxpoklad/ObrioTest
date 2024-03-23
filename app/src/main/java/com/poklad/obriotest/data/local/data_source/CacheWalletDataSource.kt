package com.poklad.obriotest.data.local.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.poklad.obriotest.data.common.data_sources.WalletDataSource
import com.poklad.obriotest.data.common.models.BalanceDataModel
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.common.models.TransactionDestinations
import com.poklad.obriotest.data.local.dao.BalanceDao
import com.poklad.obriotest.data.local.dao.TransactionDao
import com.poklad.obriotest.data.local.models.BalanceEntity
import com.poklad.obriotest.data.local.models.TransactionsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheWalletDataSource @Inject constructor(
    private val transactionDao: TransactionDao,
    private val balanceDao: BalanceDao
) : WalletDataSource {

    override suspend fun getCurrentBalance(coin: Coins): Flow<BalanceDataModel?> = balanceDao.observeBalance(coin)

    override suspend fun getTransactionsHistory(limit: Int): Flow<PagingData<TransactionDataModel>> {
        val pagingConfig = PagingConfig(pageSize = limit)
        return Pager(config = pagingConfig, pagingSourceFactory = { transactionDao.getAllTransactions() }).flow
            .map { pagingData -> pagingData.map { it } }
    }

    override suspend fun makeTranslation(amount: Float, coin: Coins, transactionDestinations: TransactionDestinations): Boolean {
        val totalAmount = (balanceDao.getBalance(coin)?.amount ?: 0f).let {
            if (transactionDestinations == TransactionDestinations.DEPOSIT) it + amount
            else it - amount
        }

        if (totalAmount < 0) return false

        val balanceEntity = BalanceEntity(coin = coin, amount = totalAmount)

        val transaction = TransactionsEntity(
            destination = transactionDestinations,
            coin = coin,
            transactionAmount = amount,
            totalAmount = totalAmount,
            time = System.currentTimeMillis()
        )

        val transactionId = transactionDao.addTransaction(transaction)

        if (transactionId == -1L) return false

        val balanceUpdateResult = balanceDao.updateBalance(balanceEntity)

        if (balanceUpdateResult == -1L) {
            transactionDao.dropTransaction(transactionId)
        }
        return true
    }
}