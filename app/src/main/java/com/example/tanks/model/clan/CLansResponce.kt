package com.example.tanks.model.clan

import com.example.tanks.model.BaseListResponse
import com.google.gson.annotations.SerializedName

data class ClansResponse(
    @SerializedName("status")
    override val status: String,
    @SerializedName("data")
    override val data: List<Clan>
) : BaseListResponse<Clan>
