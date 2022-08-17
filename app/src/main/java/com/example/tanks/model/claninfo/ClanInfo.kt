package com.example.tanks.model.claninfo

import com.google.gson.annotations.SerializedName

data class ClanInfo(
    @SerializedName("clan_id")
    val clan_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("members_count")
    val members_count: Int,
)
