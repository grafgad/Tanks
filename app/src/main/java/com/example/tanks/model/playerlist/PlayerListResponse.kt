package com.example.tanks.model.playerlist

import com.example.tanks.model.BaseListResponse
import com.google.gson.annotations.SerializedName

data class PlayerListResponse(
    @SerializedName("status") override val status: String,
    @SerializedName("data") override val data: List<PlayerList>
) : BaseListResponse<PlayerList>
