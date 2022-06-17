package com.example.tanks.presentation.players

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tanks.Api
import com.example.tanks.ApiDataSource
import com.example.tanks.customOnError
import com.example.tanks.model.player.Player
import com.example.tanks.presentation.BaseViewModel
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.reactivestreams.Subscription

class PlayerViewModel : BaseViewModel() {

    private val _playerList: BehaviorRelay<List<Player>> = BehaviorRelay.createDefault(emptyList())
    val playerList: Observable<List<Player>> = _playerList

    fun onSearchClicked(nickname: String){
        searchSubscription?.dispose()  //обнуляем предыдущий запрос
        val searchSubscription = apiDataSource.getPlayersList(nickname)
            .subscribeBy(
                onError = {
                    customOnError(it)
                },
                onSuccess = {
                    _playerList.accept(it.data)
                    Log.d("LLLLLLL", it.toString())
                }
            )
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }


}