package com.example.tanks.presentation

import androidx.lifecycle.ViewModel
import com.example.tanks.Api
import com.example.tanks.ApiDataSource
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {
    val apiDataSource: ApiDataSource = Api.getApiDataSource()
    var searchSubscription: Disposable? = null
    val compositeDisposable = CompositeDisposable()
//    val _observable: BehaviorRelay<List<Any>> = BehaviorRelay.createDefault(emptyList())
//    val customObservable: Observable<List<Any>> = _observable

//    fun onActionHappened(requiredData: String){
//        searchSubscription?.dispose()
//        val newSsearchSubscription = apiDataSource
//    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}