package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class MatchItems(

    @SerializedName("dateEvent")
    var matchSchedule: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayTeam")
    var awayScore: String? = null
)