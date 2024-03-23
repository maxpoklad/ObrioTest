package com.poklad.obriotest.domain.mapper

import com.poklad.obriotest.data.common.models.CurrencyDataModel
import com.poklad.obriotest.domain.base.Mapper
import com.poklad.obriotest.presentation.model.CurrencyPresentationModel
import javax.inject.Inject

class CurrencyDomainMapper @Inject constructor() : Mapper<CurrencyDataModel, CurrencyPresentationModel> {

    override fun map(data: CurrencyDataModel) = CurrencyPresentationModel(
        rate = data.rate,
        currency = data.currency.name,
        coin = data.coins.name
    )
}