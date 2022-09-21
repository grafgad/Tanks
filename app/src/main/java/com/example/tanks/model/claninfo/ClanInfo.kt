package com.example.tanks.model.claninfo

import com.google.gson.annotations.SerializedName

data class ClanInfo(
    @SerializedName("clan_id") val clan_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("members_count") val members_count: Int,
    @SerializedName("description") val description: String,
    @SerializedName("motto") val motto: String,
    @SerializedName("emblems") val emblems: EmblemX256,
) {
    data class EmblemResponseX256(
        @SerializedName("wowp") val wowp: String
    )

    data class EmblemX256(
        @SerializedName("x256") val x256: EmblemResponseX256
    )
}
