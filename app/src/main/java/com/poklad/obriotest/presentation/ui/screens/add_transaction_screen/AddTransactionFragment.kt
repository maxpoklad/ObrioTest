package com.poklad.obriotest.presentation.ui.screens.add_transaction_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.poklad.obriotest.databinding.FragmentSecondScreenBinding
import com.poklad.obriotest.presentation.ui.base.BaseFragment
import com.poklad.obriotest.presentation.ui.screens.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddTransactionFragment : BaseFragment<FragmentSecondScreenBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentSecondScreenBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destinationsList = viewModel.getPossibleDestinations()
        binding.spinnerTransactionCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, destinationsList).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.btnAddTransaction.setOnClickListener {
            val destination = binding.spinnerTransactionCategory.selectedItem.toString()
            val amount = binding.etTransactionAmount.editText?.text?.toString()?.takeIf { it.isNotBlank() }?.toFloatOrNull()
            if (amount != null && destination.isNotBlank()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.isSucceedTransactionFlow.collectLatest { isSucceed ->
                        if (isSucceed) {
                            findNavController().navigateUp()
                        } else {
                            Toast.makeText(requireContext(), "Error: Not enough money", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                viewModel.makeTransaction(amount, destination)
            }
        }
    }
}