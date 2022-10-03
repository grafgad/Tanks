package com.example.tanks.apisource

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class InfoDeserializer<T: Any>(
    private val clazz: Class<T>
): JsonDeserializer<T> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T? {
        val jsonObject = json?.asJsonObject ?: return null
        val key = jsonObject.keySet()?.first()?.toString() ?: return null
        val clanJsonObject = jsonObject.get(key) ?: return null
        return Gson().fromJson(clanJsonObject, clazz)
    }
}

inline fun <reified T: Any> createInfoDeserializer(): InfoDeserializer<T> {
    return InfoDeserializer(T::class.java)
}