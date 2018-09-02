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
                .appendQueryParameter("id","4328")
                .build()
                .toString()
    }
}