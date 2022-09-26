package com.example.tanks.model.playerinfo

import com.google.gson.annotations.SerializedName

data class PlayerInfo(
    @SerializedName("account_id") val accountId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("global_rating") val global_rating: Int,
    @SerializedName("clan_id") val clan_id: Int,
    @SerializedName("created_at") val created_at: Long,
    @SerializedName("last_battle_time") val last_battle_time: Long,
    @SerializedName("statistics") val statistics: Statistics
) {
    data class Statistics(
        @SerializedName("trees_cut") val treesCut: Int,
        @SerializedName("all") val all: AllResponse
    ) {
        data class AllResponse(
            @SerializedName("max_frags") val maxFrags: Int,
            @SerializedName("battles") val battles: Int
        )
    }
}
