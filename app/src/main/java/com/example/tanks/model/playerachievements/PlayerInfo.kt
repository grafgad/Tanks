package com.example.tanks.model.playerachievements

import com.google.gson.annotations.SerializedName

data class PlayerInfo(
    @SerializedName ("global_rating") val global_rating: Int,
    @SerializedName ("clan_id") val clan_id: Int,
    @SerializedName ("created_at") val created_at: Long,
    @SerializedName ("last_battle_time") val last_battle_time: Long,
    @SerializedName ("nickname") val nickname: String
)
