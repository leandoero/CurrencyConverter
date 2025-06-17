package com.example.currencyconverter.repository

import com.example.currencyconverter.data.model.CurrencyResponse

interface CurrencyRepository{
    suspend fun getData(): CurrencyResponse
}
