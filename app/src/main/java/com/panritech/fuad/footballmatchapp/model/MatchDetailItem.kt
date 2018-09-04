package com.panritech.fuad.footballmatchapp.model

import com.google.gson.annotations.SerializedName

data class MatchDetailItem(

        @SerializedName("strHomeFormation")
        val homeFormation: String? = "",

        @SerializedName("strAwayFormation")
        val awayFormation: String? = "",

        @SerializedName("strHomeGoalDetails")
        val homeGoalDetails: String? = "",

        @SerializedName("strAwayGoalDetails")
        val awayGoalDetails: String? = "",

        @SerializedName("strHomeRedCards")
        val homeRedCard: String? = "",

        @SerializedName("strAwayRedCards")
        val awayRedCard: String? = "",

        @SerializedName("strHomeYellowCards")
        val homeYellowCard: String? = "",

        @SerializedName("strAwayYellowCards")
        val awayYellowCard: String? = "",

        @SerializedName("strHomeLineupGoalkeeper")
        val homeGoalkeeper: String? = "",

        @SerializedName("strAwayLineupGoalkeeper")
        val awayGoalkeeper: String? = "",

        @SerializedName("strHomeLineupDefense")
        val homeDefense: String? = "",

        @SerializedName("strAwayLineupDefense")
        val awayDefense: String? = "",

        @SerializedName("strHomeLineupMidfield")
        val homeMidfield: String? = "",

        @SerializedName("strAwayLineupMidfield")
        val awayMidfield: String? = "",

        @SerializedName("strHomeLineupForward")
        val homeForward: String? = "",

        @SerializedName("strAwayLineupForward")
        val awayForward: String? = "",

        @SerializedName("strHomeLineupSubstitutes")
        val homeSubstitutes: String? = "",

        @SerializedName("strAwayLineupSubstitutes")
        val awaySubstitutes: String? = ""
)