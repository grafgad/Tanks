package com.example.tanks.di

import com.example.tanks.model.playerinfo.PlayerInfo
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PlayerInfoDeserializer: JsonDeserializer<PlayerInfo?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PlayerInfo? {
        val jsonObject = json?.asJsonObject ?: return null
        val key = jsonObject.keySet()?.first()?.toString() ?: return null
        val playerJsonObject = jsonObject.get(key) ?: return null
        return Gson().fromJson(playerJsonObject, PlayerInfo::class.java)
    }
}