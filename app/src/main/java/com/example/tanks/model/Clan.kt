package com.example.tanks.model

import com.google.gson.annotations.SerializedName

data class Clan(
    @SerializedName("clan_id") val clan_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("members_count") val members_count: Int,
    

)
