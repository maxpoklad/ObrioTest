package com.poklad.obriotest.data.local.database

import com.poklad.obriotest.data.local.dao.BalanceDao
import com.poklad.obriotest.data.local.dao.CurrencyDao
import com.poklad.obriotest.data.local.dao.TransactionDao

interface AppDatabase {
    fun getTransactionDao(): TransactionDao
    fun getBalanceDao(): BalanceDao
    fun getCurrencyDao(): CurrencyDao
}