package com.panritech.fuad.footballmatchapp.api

import android.net.Uri
import com.panritech.fuad.footballmatchapp.BuildConfig

object TheSportDBApi {
    fun getMach(league: String? = "4328"): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id",league)
                .build()
                .toString()
    }

    fun getNextMatch(league: String? = "4328"): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id",league)
                .build()
                .toString()
    }

    fun getMatchDetail(events: String? = ""): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id",events)
                .build()
                .toString()
    }

    fun getTeamImage(team: String? = ""): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchteams.php")
                .appendQueryParameter("t",team)
                .build()
                .toString()
    }
}