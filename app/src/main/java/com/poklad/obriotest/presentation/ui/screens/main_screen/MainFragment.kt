package com.poklad.obriotest.presentation.ui.screens.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.poklad.obriotest.R
import com.poklad.obriotest.databinding.FragmentBitcoinCostsBinding
import com.poklad.obriotest.presentation.ui.base.BaseFragment
import com.poklad.obriotest.presentation.ui.screens.MainViewModel
import com.poklad.obriotest.presentation.ui.adapter.TransactionsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentBitcoinCostsBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    @Inject
    internal lateinit var transactionListAdapter: TransactionsListAdapter

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentBitcoinCostsBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupUi()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currencyRateUsdFlow.collectLatest { currencyUSD ->
                binding.tvBitcoinToDollarRate.text =
                    getString(R.string.s_to_s_rate, currencyUSD.currency, currencyUSD.coin, currencyUSD.rate)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.balanceFlow.collectLatest {
                    binding.tvBalance.text = it.amount.toString()
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.transactionHistoryFlow.collectLatest {
                    transactionListAdapter.submitData(it)
                    binding.rvTransactionList.scrollToPosition(0)
                }

            }
        }
    }

    private fun setupUi() {
        binding.rvTransactionList.adapter = transactionListAdapter
        binding.btnDeposit.setOnClickListener {
            openDepositDialog()
        }
        binding.btnAddTransaction.setOnClickListener {
            openCreateTransactionScreen()
        }
    }

    private fun openCreateTransactionScreen() {
        findNavController().navigate(R.id.action_mainFragment_to_addTransactionFragment)
    }

    private fun openDepositDialog() {
        findNavController().navigate(R.id.action_mainFragment_to_depositBitcoinsDialogFragment)
    }
}