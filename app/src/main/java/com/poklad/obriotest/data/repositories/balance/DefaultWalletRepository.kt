package com.poklad.obriotest.data.repositories.balance

import com.poklad.obriotest.data.common.data_sources.WalletDataSource
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.TransactionDestinations
import javax.inject.Inject

class DefaultWalletRepository @Inject constructor(private val dataSource: WalletDataSource): WalletRepository {

    override suspend fun getTransactionHistory(limit: Int) = dataSource.getTransactionsHistory(limit)

    override suspend fun observeBalanceBTC() = dataSource.getCurrentBalance(Coins.BTC)

    override suspend fun depositCoinsBTC(amount: Float) = dataSource.makeTranslation(amount, Coins.BTC, TransactionDestinations.DEPOSIT)

    override suspend fun makeTransactionBTC(amount: Float, destination: TransactionDestinations) = dataSource.makeTranslation(amount, Coins.BTC, destination)

    override fun getPossibleDestinations(): List<String> = TransactionDestinations.entries
        .filter { it != TransactionDestinations.DEPOSIT }
        .map { it.name }
}