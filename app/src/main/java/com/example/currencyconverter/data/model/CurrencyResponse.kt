package com.example.currencyconverter.data.model

data class CurrencyResponse(
    val disclaimer: String,
    val date: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)

