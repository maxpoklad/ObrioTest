package com.poklad.obriotest.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poklad.obriotest.data.common.models.BalanceDataModel
import com.poklad.obriotest.data.common.models.Coins

@Entity
data class BalanceEntity(
    @PrimaryKey
    override val coin: Coins,
    override val amount: Float
): BalanceDataModel