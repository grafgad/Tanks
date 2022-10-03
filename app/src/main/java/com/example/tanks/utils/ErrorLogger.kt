package com.example.tanks.utils

import android.util.Log

object ErrorLogger {
    fun logThrowable(throwable: Throwable) {
        Log.e("ERROR_LOG", "Error", throwable)
    }
}

object SuccessLogger {
    fun logAnswer(T: Any) {
        Log.d("SUCCESS_LOG", T.toString())
    }
}