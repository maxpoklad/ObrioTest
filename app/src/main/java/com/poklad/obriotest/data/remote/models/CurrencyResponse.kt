package com.poklad.obriotest.data.remote.models

import com.google.gson.annotations.SerializedName
import com.poklad.obriotest.data.common.models.Coins
import com.poklad.obriotest.data.common.models.Currencies
import com.poklad.obriotest.data.common.models.CurrencyDataModel

data class CurrencyInfoResponse(
	@SerializedName("chartName")
	val chartName: String,
	@SerializedName("bpi")
	val bpi: Bpi,
	@SerializedName("time")
	val time: Time,
	@SerializedName("disclaimer")
	val disclaimer: String
)

data class CurrencyResponse(
	@SerializedName("symbol")
	val symbol: String,
	@SerializedName("rate_float")
	val rateFloat: Float,
	@SerializedName("code")
	val code: String,
	@SerializedName("rate")
	val rate: String,
	@SerializedName("description")
	val description: String
) {
	fun convertToCurrencyDataModel(coins: Coins) = object : CurrencyDataModel {
		override val currency: Currencies = Currencies.valueOf(code)
		override val coins: Coins = coins
		override val rate: Float = rateFloat
		override val lastUpdateTime: Long = System.currentTimeMillis()
	}
}

data class Time(
	@SerializedName("updateduk")
	val updateduk: String,
	@SerializedName("updatedISO")
	val updatedISO: String,
	@SerializedName("updated")
	val updated: String
)

data class Bpi(
	@SerializedName("EUR")
	val eur: CurrencyResponse,
	@SerializedName("GBP")
	val gbp: CurrencyResponse,
	@SerializedName("USD")
	val usd: CurrencyResponse
)
