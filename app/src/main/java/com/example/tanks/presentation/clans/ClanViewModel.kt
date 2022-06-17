package com.example.tanks.presentation.clans

import android.util.Log
import com.example.tanks.customOnError
import com.example.tanks.model.clan.Clan
import com.example.tanks.presentation.BaseViewModel
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ClanViewModel : BaseViewModel() {

    private val _clanList: BehaviorRelay<List<Clan>> = BehaviorRelay.createDefault(emptyList())
    val clanList: Observable<List<Clan>> = _clanList

    fun onSearchClicked(clanName: String) {
        searchSubscription?.dispose()
        val searchSubscription = apiDataSource.getClanList(clanName)
            .subscribeBy(
                onError = { customOnError(it) },
                onSuccess = {
                    _clanList.accept(it.data)
                    Log.d("LLLLLLL", it.toString())
                }
            )
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }


}