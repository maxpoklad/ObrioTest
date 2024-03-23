package com.poklad.obriotest.utils

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchersProvider {
    val ioDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val unconfinedDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
}