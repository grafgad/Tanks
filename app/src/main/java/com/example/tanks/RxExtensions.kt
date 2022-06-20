package com.example.tanks

import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.subscribeSafely(
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {}
): Disposable {
    return subscribeBy(
        onError = {
            ErrorLogger.logThrowable(it)
            onError.invoke(it)
        },
        onSuccess = onSuccess
    )
}

