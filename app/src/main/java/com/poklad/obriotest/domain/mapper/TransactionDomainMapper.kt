package com.poklad.obriotest.domain.mapper

import com.poklad.obriotest.data.common.models.TransactionDataModel
import com.poklad.obriotest.data.common.models.TransactionDestinations
import com.poklad.obriotest.domain.base.Mapper
import com.poklad.obriotest.presentation.model.TransactionPresentationModel
import javax.inject.Inject

class TransactionDomainMapper @Inject constructor() : Mapper<TransactionDataModel, TransactionPresentationModel> {

    override fun map(data: TransactionDataModel) = TransactionPresentationModel(
        totalAmount = data.totalAmount,
        destination = data.destination.name,
        transactionAmount = data.transactionAmount,
        isDeposit = data.destination == TransactionDestinations.DEPOSIT,
        coin = data.coin.name,
        time = data.time
    )

}