package com.example.tanks.apisource

import com.example.tanks.apisource.model.claninfo.ClanInfoResponse
import com.example.tanks.apisource.model.clanlist.ClansListResponse
import com.example.tanks.apisource.model.playerlist.PlayerListResponse
import com.example.tanks.apisource.model.playerinfo.PlayerInfoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDataSource {

    @GET("wot/clans/list/")
    fun getClanList(
        @Query("search")clanName: String
    ): Single<ClansListResponse>

    @GET("/wot/account/list/")
    fun getPlayersList(
        @Query("search") player: String
    ): Single<PlayerListResponse>

    @GET("/wot/account/info/")
    fun getPlayerInfo(
        @Query("account_id") account_id: Int
    ): Single<PlayerInfoResponse>

    @GET("/wot/clans/info/")
    fun getClanInfo(
        @Query("clan_id") clan_id: Int
    ): Single<ClanInfoResponse>
}