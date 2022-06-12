package com.example.tanks.presentation.players

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tanks.Api
import com.example.tanks.ApiDataSource
import com.example.tanks.model.player.Player
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.reactivestreams.Subscription

class PlayerViewModel : ViewModel() {
    private val apiDataSource: ApiDataSource = Api.getApiDataSource()
    private val compositeDisposable = CompositeDisposable()
    private var searchSubscription: Disposable? = null
    private val _playerList: BehaviorRelay<List<Player>> = BehaviorRelay.createDefault(emptyList())
    val playerList: Observable<List<Player>> = _playerList

    fun onSearchClicked(nickname: String){
        searchSubscription?.dispose()
        val searchSubscription = apiDataSource.getPlayersList(nickname)
            .subscribeBy(
                onError = {
                    Log.d("LLLLLLL", "Error", it)
                },
                onSuccess = {
                    _playerList.accept(it.data)
                    Log.d("LLLLLLL", it.toString())
                }
            )
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }



    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}