package com.poklad.obriotest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.local.models.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrencyInfo(currencyEntity: CurrencyEntity)

    @Query("SELECT * FROM CurrencyEntity WHERE currency = :currency AND coins = :coins")
    suspend fun getCurrencyInfo(currency: Currencies, coins: Coins): CurrencyEntity

    @Query("SELECT * FROM CurrencyEntity WHERE currency = :currency AND coins = :coins")
    fun observeCurrencyInfo(currency: Currencies, coins: Coins): Flow<CurrencyEntity?>
}