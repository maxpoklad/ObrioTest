package com.poklad.obriotest.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel

@Entity
data class CurrencyEntity(
    @PrimaryKey
    val id: String,
    override val lastUpdateTime: Long,
    override val currency: Currencies,
    override val rate: Float,
    override val coins: Coins,
) : CurrencyDataModel