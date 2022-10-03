package com.example.tanks.apisource.model.claninfo

import com.google.gson.annotations.SerializedName

data class ClanInfo(
    @SerializedName("clan_id") val clan_id: Int,
    @SerializedName("emblems") val clanImage: EmblemX256,
    @SerializedName("name") val name: String,
    @SerializedName("tag") val clanTag: String,
    @SerializedName("description") val description: String,
    @SerializedName("members_count") val members_count: Int,
    @SerializedName("motto") val motto: String,
) {
    data class EmblemX256(
        @SerializedName("x256") val x256: EmblemResponseX256
    ) {
        data class EmblemResponseX256(
            @SerializedName("wowp") val wowp: String
        )
    }
}
