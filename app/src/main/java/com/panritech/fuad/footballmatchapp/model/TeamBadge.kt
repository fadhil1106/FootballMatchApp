package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class TeamBadge(
        @SerializedName("strTeamBadge")
        val teamBadge: String? = ""
)