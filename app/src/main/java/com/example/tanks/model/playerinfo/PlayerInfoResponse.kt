package com.example.tanks.model.playerinfo

import com.example.tanks.model.BaseDetailResponse
import com.google.gson.annotations.SerializedName

data class PlayerInfoResponse(
    @SerializedName("status") override val status: String,
    @SerializedName("data") override val data: PlayerInfo
) : BaseDetailResponse<PlayerInfo>
