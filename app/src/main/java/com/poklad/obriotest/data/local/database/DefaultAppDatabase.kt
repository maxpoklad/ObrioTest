package com.poklad.obriotest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poklad.obriotest.data.local.dao.BalanceDao
import com.poklad.obriotest.data.local.dao.CurrencyDao
import com.poklad.obriotest.data.local.models.TransactionsEntity
import com.poklad.obriotest.data.local.dao.TransactionDao
import com.poklad.obriotest.data.local.models.BalanceEntity
import com.poklad.obriotest.data.local.models.CurrencyEntity
import com.poklad.obriotest.utils.DatabaseConstants

@Database(
    entities = [TransactionsEntity::class, BalanceEntity::class, CurrencyEntity::class],
    version = DatabaseConstants.DATABASE_VERSION,
    exportSchema = false
)
abstract class DefaultAppDatabase: RoomDatabase(), AppDatabase {
    abstract override fun getTransactionDao(): TransactionDao
    abstract override fun getBalanceDao(): BalanceDao
    abstract override fun getCurrencyDao(): CurrencyDao

    companion object {
        const val DB_NAME = "crypto_app_db"
    }

}