package com.poklad.obriotest.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.common.models.TransactionDestinations

@Entity
data class TransactionsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    override val destination: TransactionDestinations,
    override val coin: Coins,
    override val transactionAmount: Float,
    override val totalAmount: Float,
    override val time: Long,
): TransactionDataModel
