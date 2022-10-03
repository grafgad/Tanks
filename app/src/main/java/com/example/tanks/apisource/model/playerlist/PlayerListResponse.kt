package com.example.tanks.apisource.model.playerlist

import com.example.tanks.apisource.model.BaseListResponse
import com.google.gson.annotations.SerializedName

data class PlayerListResponse(
    @SerializedName("status") override val status: String,
    @SerializedName("data") override val data: List<PlayerList>
) : BaseListResponse<PlayerList>
