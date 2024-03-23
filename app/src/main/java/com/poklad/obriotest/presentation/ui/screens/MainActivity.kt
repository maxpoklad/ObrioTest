package com.poklad.obriotest.presentation.ui.screens

import android.view.LayoutInflater
import com.poklad.obriotest.databinding.ActivityMainBinding
import com.poklad.obriotest.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)
}