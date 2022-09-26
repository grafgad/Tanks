package com.example.tanks.presentation.playerslist

import com.example.tanks.di.service_locator.ApiDataSource
import com.example.tanks.model.playerlist.PlayerList
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class PlayerListViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private val _playerListList: BehaviorRelay<List<PlayerList>> =
        BehaviorRelay.createDefault(emptyList())
    val playerList: Observable<List<PlayerList>> = _playerListList

    fun onSearchClicked(nickname: String) {
        searchSubscription?.dispose()  //обнуляем предыдущий запрос
        if (nickname.length >= 3) {
            val searchSubscription = apiDataSource.getPlayersList(nickname)
                .subscribeSafely {
                    _playerListList.accept(it.data)
                }
            this.searchSubscription = searchSubscription
            compositeDisposable.add(searchSubscription)
        } else {
            _playerListList.accept(listOf(PlayerList(0, "Введите больше 3 символов для поиска")))
        }
    }
}