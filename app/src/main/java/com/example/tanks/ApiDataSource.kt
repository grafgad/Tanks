package com.example.tanks

import com.example.tanks.model.clan.ClansResponse
import com.example.tanks.model.player.PlayerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDataSource {

    @GET("")
    fun getDefaultResponse(
        @Query("")any: Any
    ): Single<Any>

    @GET("wot/clans/list/")
    fun getClanList(
        @Query("search")clanName: String
    ): Single<ClansResponse>

    @GET("/wot/account/list/")
    fun getPlayersList(
        @Query("search") player: String
    ): Single<PlayerResponse>
}