package com.example.tanks.apisource.model.clanlist

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
    val emblems: EmblemX195
) {
    data class EmblemX195(
        @SerializedName("x195") val x195: EmblemResponseX195
    )

    data class EmblemResponseX195(
        @SerializedName("portal") val portal: String
    )
}