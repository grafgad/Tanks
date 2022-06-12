package com.example.tanks.presentation.clans

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tanks.ApiDataSource
import com.example.tanks.Api
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class ClanViewModel : ViewModel() {
    private val apiDataSource: ApiDataSource = Api.getApiDataSource()
    private val compositeDisposable = CompositeDisposable()

    init{
        apiDataSource.getClanList()
            .subscribeBy(
                onError = {
                    Log.d("LLLLLLL", "Error", it)
                },
                onSuccess = {
                    Log.d("LLLLLLL", it.toString())
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}