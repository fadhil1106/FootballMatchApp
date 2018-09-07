package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class MatchItem(
        @SerializedName("idEvent")
    var eventId: String? = "",

        @SerializedName("dateEvent")
    var matchSchedule: String? = "",

        @SerializedName("strHomeTeam")
    var homeTeam: String? = "",

        @SerializedName("strAwayTeam")
    var awayTeam: String? = "",

        @SerializedName("intHomeScore")
    var homeScore: String? = "",

        @SerializedName("intAwayScore")
    var awayScore: String? = ""
)