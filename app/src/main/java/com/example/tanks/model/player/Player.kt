package com.example.tanks.model.player

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("account_id")
    val account_id: Int,
    @SerializedName("nickname")
    val nickname: String
)