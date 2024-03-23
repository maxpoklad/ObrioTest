package com.poklad.obriotest.presentation.ui.screens

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.poklad.obriotest.domain.usecases.BalanceMultipleUseCases
import com.poklad.obriotest.domain.usecases.CurrencyMultipleUseCases
import com.poklad.obriotest.domain.usecases.TransactionMultipleUseCases
import com.poklad.obriotest.presentation.model.BalancePresentationModel
import com.poklad.obriotest.presentation.model.CurrencyPresentationModel
import com.poklad.obriotest.presentation.model.TransactionPresentationModel
import com.poklad.obriotest.presentation.ui.base.BaseViewModel
import com.poklad.obriotest.utils.CoroutineDispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val balanceUseCases: BalanceMultipleUseCases,
    private val currencyUseCases: CurrencyMultipleUseCases,
    private val transactionMultipleUseCases: TransactionMultipleUseCases
) : BaseViewModel() {

    private val _transactionHistoryFlow =
        MutableStateFlow<PagingData<TransactionPresentationModel>?>(null)
    val transactionHistoryFlow = _transactionHistoryFlow.filterNotNull()

    private val _currencyRateUsdFlow = MutableStateFlow(CurrencyPresentationModel())
    val currencyRateUsdFlow = _currencyRateUsdFlow.asStateFlow()

    private val _balanceFlow = MutableStateFlow(BalancePresentationModel())
    val balanceFlow = _balanceFlow.asStateFlow()

    private val _isSucceedTransactionFlow = MutableStateFlow(false)
    val isSucceedTransactionFlow = _isSucceedTransactionFlow

    init {
        viewModelScope.launch(coroutineDispatchersProvider.ioDispatcher + coroutineExceptionHandler) {
            balanceUseCases.observeBalanceBTC().collectLatest {
                _balanceFlow.value = it
            }
        }
        viewModelScope.launch(coroutineDispatchersProvider.ioDispatcher + coroutineExceptionHandler) {
            currencyUseCases.observeCurrencyBTCtoUSD().collectLatest {
                _currencyRateUsdFlow.value = it
            }
        }
        viewModelScope.launch(coroutineDispatchersProvider.ioDispatcher + coroutineExceptionHandler) {
            transactionMultipleUseCases.observeTransactionHistory(TRANSACTION_HISTORY)
                .cachedIn(this)
                .collectLatest { _transactionHistoryFlow.value = it }
        }

        initData()
    }

    fun makeTransaction(amount: Float, destination: String) =
        viewModelScope.launch(coroutineDispatchersProvider.ioDispatcher + coroutineExceptionHandler) {
            showLoader()
            val isSucceed = transactionMultipleUseCases.makeTransactionBTC(amount, destination)
            hideLoader()
            _isSucceedTransactionFlow.value = isSucceed
        }

    fun getPossibleDestinations() = transactionMultipleUseCases.getPossibleDestinations()

    fun updateBalance(amount: Float) =
        viewModelScope.launch(coroutineDispatchersProvider.ioDispatcher + coroutineExceptionHandler) {
            balanceUseCases.depositBTC(amount)
        }

    private fun initData() =
        viewModelScope.launch(coroutineDispatchersProvider.ioDispatcher + coroutineExceptionHandler) {
            currencyUseCases.refreshCurrencyBTC()
        }

    companion object {
        const val TRANSACTION_HISTORY = 20
    }
}