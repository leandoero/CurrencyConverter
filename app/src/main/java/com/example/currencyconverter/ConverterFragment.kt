package com.example.currencyconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.currencyconverter.databinding.FragmentConverterBinding

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentConverterBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
