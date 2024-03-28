package com.poklad.obriotest.data.remote

import com.poklad.obriotest.data.remote.models.CurrencyInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {

    @GET("bpi/currentprice.json")
    suspend fun getCurrencyUSDInfo(): CurrencyInfoResponse?
}