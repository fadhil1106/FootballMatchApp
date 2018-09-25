package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class PlayersItem(
        @SerializedName("idPlayer")
        val playerId: String? = "",

        @SerializedName("strPlayer")
        val playerName: String? = "",

        @SerializedName("strCutout")
        val playerImage: String? = ""
)