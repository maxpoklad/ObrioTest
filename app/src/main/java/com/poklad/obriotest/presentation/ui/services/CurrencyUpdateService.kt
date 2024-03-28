package com.poklad.obriotest.presentation.ui.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.poklad.obriotest.domain.usecases.CurrencyMultipleUseCases
import com.poklad.obriotest.utils.CoroutineDispatchersProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@AndroidEntryPoint
class CurrencyUpdateService : Service() {

    @Inject
    internal lateinit var dispatcherProvider: CoroutineDispatchersProvider
    @Inject
    internal lateinit var currencyMultipleUseCases: CurrencyMultipleUseCases

    private val binder = LocalBinder()
    private var controlJob: Job = Job()
    private val serviceScope by lazy { CoroutineScope(dispatcherProvider.ioDispatcher + controlJob) }


    override fun onBind(intent: Intent): IBinder {
        if (controlJob.isActive) controlJob.cancel()
        if (controlJob.isCancelled) controlJob = Job()

        serviceScope.launch {
            while (isActive) {
                val delayTime = ((TIME_VALUE - currencyMultipleUseCases.getTimeFromLastUpdate()).takeIf { it > 0 } ?: 0L)

                delay(delayTime)
                currencyMultipleUseCases.refreshCurrencyBTC()
            }
        }
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        controlJob.cancel()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        controlJob.cancel()
    }


    companion object {
        private const val TIME_VALUE = 1 * 60 * 60 * 1_000L
    }

    inner class LocalBinder : Binder()
}