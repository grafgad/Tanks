package com.example.tanks.presentation.clanslist

import com.example.tanks.di.service_locator.ApiDataSource
import com.example.tanks.model.clanlist.ClanList
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ClanViewModel @Inject constructor (
    private val apiDataSource : ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private val _clanListList: BehaviorRelay<List<ClanList>> = BehaviorRelay.createDefault(emptyList())
    val clanList: Observable<List<ClanList>> = _clanListList

    fun onSearchClicked(clanName: String) {
        searchSubscription?.dispose()
        val searchSubscription = apiDataSource.getClanList(clanName)
            .subscribeSafely {
                _clanListList.accept(it.data)
            }
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }
}