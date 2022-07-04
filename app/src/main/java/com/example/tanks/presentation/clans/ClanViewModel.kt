package com.example.tanks.presentation.clans

import com.example.tanks.di.ServiceLocator
import com.example.tanks.model.clan.Clan
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

class ClanViewModel(
    private val serviceLocator : ServiceLocator
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private val _clanList: BehaviorRelay<List<Clan>> = BehaviorRelay.createDefault(emptyList())
    val clanList: Observable<List<Clan>> = _clanList

    fun onSearchClicked(clanName: String) {
        searchSubscription?.dispose()
        val searchSubscription = serviceLocator.apiDataSource.getClanList(clanName)
            .subscribeSafely {
                _clanList.accept(it.data)
            }
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }
}