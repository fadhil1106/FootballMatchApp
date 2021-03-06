package com.panritech.fuad.footballmatchapp.presenter

import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.MatchItemResponse
import com.panritech.fuad.footballmatchapp.view.MatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val matchView: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {
    fun getMatchList(league: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatch(league))
                    , MatchItemResponse::class.java
            )

            uiThread {
                matchView.showMatchList(data.events)
            }
        }
    }

    fun getNextMatchList(league: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(league))
                    , MatchItemResponse::class.java
            )

            uiThread {
                matchView.showMatchList(data.events)
            }
        }
    }
}