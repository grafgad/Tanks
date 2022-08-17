package com.example.tanks.model.playerinfo

import com.example.tanks.model.PlayerBaseResponse
import com.google.gson.annotations.SerializedName

data class PlayerInfoBaseResponse(
    @SerializedName("status")
    override val status: String,
    @SerializedName("data")
    override val data: PlayerString
) : PlayerBaseResponse
