package com.poklad.obriotest.presentation.ui.screens.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.poklad.obriotest.databinding.DialogFragmentDepositBitcoinsBinding
import com.poklad.obriotest.presentation.ui.screens.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepositBitcoinsDialogFragment : DialogFragment() {
    private lateinit var binding: DialogFragmentDepositBitcoinsBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentDepositBitcoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnDeposit.setOnClickListener {
            val amountText = binding.editTextName.text.toString()
            val amount = amountText.toFloatOrNull()
            if (amount != null) {
                viewModel.updateBalance(amount)
                dismiss()
            }
        }
    }
}