package com.example.tanks.model.clanlist

import com.google.gson.annotations.SerializedName
import java.util.*


data class ClanList(
    @SerializedName("clan_id")
    val clan_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("members_count")
    val members_count: Int,
//    @SerializedName("emblems")
//    val emblems: EmblemResponse
)