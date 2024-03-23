package com.poklad.obriotest.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poklad.obriotest.data.common.data_sources.CurrencyDataSource
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel

@Entity
data class CurrencyEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("currency")
    override val currency: Currencies,
    @ColumnInfo("rate")
    override val rate: Float,
    @ColumnInfo("coins")
    override val coins: Coins
) : CurrencyDataModel