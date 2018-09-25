package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class TeamItem(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)