package com.example.tanks.presentation.clanslist

import com.example.tanks.apisource.ApiDataSource
import com.example.tanks.apisource.model.clanlist.ClanList
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.utils.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ClanViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private val _clansList: BehaviorRelay<List<ClanList>> = BehaviorRelay.createDefault(emptyList())
    val clanList: Observable<List<ClanList>> = _clansList

    fun onSearchClicked(clanName: String) {
        searchSubscription?.dispose()
        if (clanName.length >= 2) {
            val searchSubscription = apiDataSource.getClanList(clanName)
                .subscribeSafely {
                    _clansList.accept(it.data)
                }
            this.searchSubscription = searchSubscription
            compositeDisposable.add(searchSubscription)
        } else {
            _clansList.accept(
                listOf(
                    ClanList(
                        0,
                        "Введите больше 2 символов для поиска",
                        0,
                        0,
                        ClanList.EmblemX195(ClanList.EmblemResponseX195(""))
                    )
                )
            )
        }
    }
}