package com.poklad.obriotest.presentation.ui.screens

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.poklad.obriotest.databinding.ActivityMainBinding
import com.poklad.obriotest.presentation.ui.services.CurrencyUpdateService
import com.poklad.obriotest.presentation.ui.base.BaseActivity
import com.poklad.obriotest.utils.connectivity.observeNetworkConnectivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var isRegretted = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
            isRegretted = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isRegretted = false
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            this@MainActivity.observeNetworkConnectivity().collect { isConnected ->
                if (isConnected) startCurrencyUpdateTask()
                else stopCurrencyUpdateTask()
            }
        }
    }

    private fun startCurrencyUpdateTask() {
        if (isRegretted) unbindService(serviceConnection)
        Intent(this@MainActivity, CurrencyUpdateService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun stopCurrencyUpdateTask() {
        if (isRegretted) unbindService(serviceConnection)
        isRegretted = false
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCurrencyUpdateTask()
    }
}