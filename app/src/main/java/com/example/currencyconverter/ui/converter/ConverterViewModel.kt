package com.example.currencyconverter.ui.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.model.CurrencyResponse
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    private val _rates = MutableStateFlow<CurrencyResponse?>(null)
    val rates: StateFlow<CurrencyResponse?> = _rates.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun fetchRates() {
        viewModelScope.launch {
            try {
                val data = currencyRepository.getData()
                _rates.value = data
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
                _rates.value = null
            }
        }
    }

}