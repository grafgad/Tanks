package com.example.tanks.presentation.playerinfo


import com.example.tanks.apisource.ApiDataSource
import com.example.tanks.model.playerinfo.PlayerInfo
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class PlayerInfoViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private var _playerInfo: BehaviorRelay<PlayerInfo> = BehaviorRelay.createDefault(
        PlayerInfo(
            0,
            "",
            0,
            0,
            0,
            0,
            PlayerInfo.Statistics(
                0,
                PlayerInfo.Statistics.AllResponse(
                    0,
                    0
                )
            )
        )
    )
    val playerInfo: Observable<PlayerInfo> = _playerInfo

    fun getPlayerInfo(accountId: Int) {
        searchSubscription?.dispose()
        val searchSubscription = apiDataSource
            .getPlayerInfo(accountId)
            .subscribeSafely {
                _playerInfo.accept(
                    it.data
                )
            }
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }

}