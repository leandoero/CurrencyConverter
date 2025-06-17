package com.example.currencyconverter.ui.converter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.currencyconverter.R
import com.example.currencyconverter.data.repository.CurrencyRepositoryImpl
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.launch

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    private val repository = CurrencyRepositoryImpl()
    val viewModel = ConverterViewModel(repository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentConverterBinding.bind(view)

        viewModel.fetchRates()

        lifecycleScope.launch {
            viewModel.rates.collect { response ->
                if (response != null) {

                    val currencies = response.rates.keys.toList()
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        currencies
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerConverted.adapter = adapter

                    binding.spinnerConverted.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                val selected = parent.getItemAtPosition(position) as String
                                val rate = response.rates[selected]
                                binding.textView.text = rate?.toString() ?: "Нет данных"
                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {}
                        }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}