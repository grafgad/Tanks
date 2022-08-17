package com.example.tanks.model

import com.example.tanks.model.playerinfo.PlayerInfo
import com.example.tanks.model.playerinfo.PlayerString

interface PlayerBaseResponse {
    val status: String
    val data: PlayerString
}