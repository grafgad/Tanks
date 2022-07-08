package com.example.tanks.presentation.clans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tanks.di.service_locator.ApiDataSource
import com.example.tanks.model.clan.Clan
import com.example.tanks.presentation.BaseViewModel
import com.example.tanks.subscribeSafely
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ClanViewModel(
    private val apiDataSource : ApiDataSource
) : BaseViewModel() {

    private var searchSubscription: Disposable? = null
    private val _clanList: BehaviorRelay<List<Clan>> = BehaviorRelay.createDefault(emptyList())
    val clanList: Observable<List<Clan>> = _clanList

    fun onSearchClicked(clanName: String) {
        searchSubscription?.dispose()
        val searchSubscription = apiDataSource.getClanList(clanName)
            .subscribeSafely {
                _clanList.accept(it.data)
            }
        this.searchSubscription = searchSubscription
        compositeDisposable.add(searchSubscription)
    }
}

@Suppress("UNCHECKED_CAST")
class ClanViewModelFactory @Inject constructor(
    private val apiDataSource : ApiDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClanViewModel(apiDataSource) as T
    }

}