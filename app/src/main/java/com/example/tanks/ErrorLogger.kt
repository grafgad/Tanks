package com.example.tanks

import android.util.Log

object ErrorLogger {
    fun logThrowable(throwable: Throwable) {
        Log.e("LLLLLLL", "Error", throwable)
    }
}
