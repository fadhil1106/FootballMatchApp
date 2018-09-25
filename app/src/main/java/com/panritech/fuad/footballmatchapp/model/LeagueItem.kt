package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class LeagueItem (

        @SerializedName("strLeague")
        val leagueName: String? = ""
)