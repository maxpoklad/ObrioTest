package com.poklad.obriotest.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.common.models.TransactionDestinations

@Entity
data class TransactionsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("destination")
    override val destination: TransactionDestinations,
    @ColumnInfo("coin")
    override val coin: Coins,
    @ColumnInfo("transactionAmount")
    override val transactionAmount: Float,
    @ColumnInfo("totalAmount")
    override val totalAmount: Float,
    @ColumnInfo("time")
    override val time: Long,
) : TransactionDataModel
