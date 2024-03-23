package com.poklad.obriotest.presentation.model

data class TransactionPresentationModel(
    val coin: String,
    val transactionAmount: Float,
    val totalAmount: Float,
    val time: Long,
    val destination: String? = null,
    val isDeposit: Boolean
)