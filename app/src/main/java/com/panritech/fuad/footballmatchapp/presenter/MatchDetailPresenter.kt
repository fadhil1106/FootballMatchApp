package com.panritech.fuad.footballmatchapp.presenter

import android.util.Log
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.view.MatchDetailView
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.MatchDetailItemResponse
import com.panritech.fuad.footballmatchapp.model.TeamBadgeResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val matchDetailView: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson)
{
    fun getMatchDetail(events: String?){
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatchDetail(events))
                    , MatchDetailItemResponse::class.java)
            uiThread {
                matchDetailView.showMatchDetail(data.events)
            }
        }
    }

    fun getBadgeUrl(teamName: String?, teamStatus: String?){
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getBadgeUrl(teamName))
                    , TeamBadgeResponse::class.java)

            uiThread {
                if(teamStatus == "home")
                    matchDetailView.showHomeBadge(data.teams)
                else
                    matchDetailView.showAwayBadge(data.teams)
            }
        }
    }
}