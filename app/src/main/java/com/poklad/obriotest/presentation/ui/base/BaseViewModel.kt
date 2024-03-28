package com.poklad.obriotest.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poklad.obriotest.utils.CoroutineDispatchersProvider
import com.poklad.obriotest.utils.logError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch(coroutineContext) {
            _errorFlow.emit(throwable)
        }
        logError(throwable.message.toString())
    }

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable?>()
    val errorFlow = _errorFlow.asSharedFlow()

    protected fun showLoader() {
        _loadingFlow.value = true
    }

    protected fun hideLoader() {
        _loadingFlow.value = false
    }
}