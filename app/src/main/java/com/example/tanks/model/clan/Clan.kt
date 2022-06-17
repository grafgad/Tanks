package com.example.tanks.model.clan

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Clan(
    @SerializedName("clan_id")
    val clan_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("members_count")
    val members_count: Int,
//    @SerializedName("emblems")
//    val emblems: List<List<Map<String, String>>>
)
