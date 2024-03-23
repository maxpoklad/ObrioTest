package com.poklad.obriotest.data.common.models

interface TransactionDataModel {
    val destination: TransactionDestinations
    val coin: Coins
    val transactionAmount: Float
    val totalAmount: Float
    val time: Long
}
