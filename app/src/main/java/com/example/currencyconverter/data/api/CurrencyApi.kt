package com.example.currencyconverter.data.api

import com.example.currencyconverter.data.model.CurrencyResponse
import retrofit2.http.GET

interface CurrencyApi {
    @GET("latest.js")
    suspend fun getData(): CurrencyResponse
}