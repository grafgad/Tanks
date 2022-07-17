package com.example.tanks.presentation.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tanks.di.service_locator.ApiDataSource
import com.example.tanks.di.service_locator.ServiceLocator
import com.example.tanks.model.player.Player
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private val _playerList: BehaviorRelay<List<Player>> = BehaviorRelay.createDefault(emptyList())
    val playerList: Observable<List<Player>> = _playerList

    fun onSearchClicked(nickname: String) {
        searchSubscription?.dispose()  //обнуляем предыдущий запрос
        val searchSubscription = apiDataSource.getPlayersList(nickname)
            .subscribeSafely {
                _playerList.accept(it.data)
            }
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }
}