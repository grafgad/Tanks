package com.example.tanks.model.player

import com.example.tanks.model.BaseListResponse
import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("status")
    override val status: String,
    @SerializedName("data")
    override val data: List<Player>
) : BaseListResponse<Player>
