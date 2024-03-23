package com.poklad.obriotest.domain.mapper

import com.poklad.obriotest.data.common.models.BalanceDataModel
import com.poklad.obriotest.domain.base.Mapper
import com.poklad.obriotest.presentation.model.BalancePresentationModel
import javax.inject.Inject

class BalanceDomainMapper @Inject constructor() : Mapper<BalanceDataModel, BalancePresentationModel> {

    override fun map(data: BalanceDataModel) = BalancePresentationModel(data.coin.name, data.amount)
}