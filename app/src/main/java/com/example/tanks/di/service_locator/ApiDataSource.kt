package com.example.tanks.di.service_locator

import com.example.tanks.model.clanlist.ClansResponse
import com.example.tanks.model.playerlist.PlayerListResponse
import com.example.tanks.model.playerinfo.PlayerInfoBaseResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDataSource {

    @GET("wot/clans/list/")
    fun getClanList(
        @Query("search")clanName: String
    ): Single<ClansResponse>

    @GET("/wot/account/list/")
    fun getPlayersList(
        @Query("search") player: String
    ): Single<PlayerListResponse>

    @GET("/wot/account/info/")
    fun getPlayerInfo(
        @Query("account_id") account_id: Int
    ): Single<PlayerInfoBaseResponse>
}