package com.poklad.obriotest.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.local.models.TransactionsEntity
import retrofit2.http.DELETE

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionsEntity ORDER BY time DESC")
    fun getAllTransactions(): PagingSource<Int, TransactionsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transactionsEntity: TransactionsEntity): Long

    @Query("DELETE FROM TransactionsEntity WHERE id = :id")
    suspend fun dropTransaction(id: Long)
}