package com.example.tanks.model.clanlist

import com.google.gson.annotations.SerializedName

data class ClanList(
    @SerializedName("clan_id")
    val clan_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("members_count")
    val members_count: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("emblems")
    val emblems: emblemx64
) {
    data class EmblemResponse(
        @SerializedName("wot") val wot: String,
        @SerializedName("portal") val portal: String
    )

    data class emblemx64(
        @SerializedName("x64") val x64: EmblemResponse
    )
}