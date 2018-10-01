package com.panritech.fuad.footballmatchapp.model.team

import com.google.gson.annotations.SerializedName

data class PlayerDetail (
        @SerializedName("strPlayer")
        val playerName: String? = "",

        @SerializedName("strDescriptionEN")
        val playerDescription: String? = "",

        @SerializedName("strPosition")
        val playerPosition: String? = "",

        @SerializedName("strHeight")
        val playerHeight: String? = "",

        @SerializedName("strWeight")
        val playerWeight: String? = "",

        @SerializedName("strFanart1")
        val playerImage: String? = ""
)