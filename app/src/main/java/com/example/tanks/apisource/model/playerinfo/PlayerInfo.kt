package com.example.tanks.apisource.model.playerinfo

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
        @SerializedName("all") val all: AllResponse,
        @SerializedName("trees_cut") val treesCut: Int
    ) {
        data class AllResponse(
            @SerializedName("battles") val battles: Int,//Проведено боёв
            @SerializedName("max_frags") val maxFrags: Int,//Максимум уничтожено за бой
            @SerializedName("max_xp") val maxXp: Int, //Максимальный опыт за бой
            @SerializedName("hits_percents") val hitsPercents: Int, //Процент попаданий
            @SerializedName("wins") val wins: Int, //Победы
            @SerializedName("draws") val draws: Int, //Ничьи
            @SerializedName("spotted") val spotted: Int, //Обнаружено противников
            @SerializedName("battle_avg_xp") val battleAvgXp: Int, //Средний опыт за бой

        )
    }
}
