package com.example.tanks.presentation.claninfo

import com.example.tanks.di.service_locator.ApiDataSource
import com.example.tanks.model.claninfo.ClanInfo
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ClanInfoViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
//    private val _clanInfo: BehaviorRelay<ClanInfo> = BehaviorRelay.createDefault(
//        ClanInfo(
//            0,"",0,"","",
//        )
//    )
//    val clanInfo: Observable<ClanInfo> = _clanInfo

    fun getClanInfo(clanId: Int) {
        searchSubscription?.dispose()
        val mSearchSubscription = apiDataSource.getClanInfo(clanId)
            .subscribeSafely {
//                _clanInfo.accept(
//                    it.data
//                )
            }
        this.searchSubscription = mSearchSubscription
        compositeDisposable.add(mSearchSubscription)
    }

}