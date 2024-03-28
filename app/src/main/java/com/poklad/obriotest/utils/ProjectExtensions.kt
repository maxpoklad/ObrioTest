package com.poklad.obriotest.utils

import android.util.Log
fun Any.tag(): String {
    return this::class.simpleName!!
}
fun Any.logError(msg: String) {
    Log.e("TAG: ${tag()}", msg)
}