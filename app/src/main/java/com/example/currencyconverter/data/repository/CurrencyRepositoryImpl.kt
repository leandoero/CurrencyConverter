package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.api.CurrencyApi
import com.example.currencyconverter.data.model.CurrencyResponse
import com.example.currencyconverter.repository.CurrencyRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyRepositoryImpl: CurrencyRepository {

    private lateinit var currencyApi: CurrencyApi

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create()) //специальный конвертор, который будет превращать ответ из json в data формат
            .build()
        currencyApi = retrofit.create(CurrencyApi::class.java)
    }

    override suspend fun getData(): CurrencyResponse {
        return currencyApi.getData()
    }

}