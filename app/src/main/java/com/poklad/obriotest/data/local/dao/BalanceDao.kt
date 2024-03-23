package com.poklad.obriotest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.local.models.BalanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {
    @Query("SELECT * FROM BalanceEntity WHERE coin = :coin")
    fun observeBalance(coin: Coins): Flow<BalanceEntity?>

    @Query("SELECT * FROM BalanceEntity WHERE coin = :coin")
    suspend fun getBalance(coin: Coins): BalanceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBalance(balance: BalanceEntity): Long
}