package com.panritech.fuad.footballmatchapp.presenter

import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.LeagueResponse
import com.panritech.fuad.footballmatchapp.model.TeamResponse
import com.panritech.fuad.footballmatchapp.view.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter (val view: TeamView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getLeagueList() {
        view.showProgressBar()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLeague()),
                    LeagueResponse::class.java
            )

            uiThread {
                view.showLeagueList(data.leagues)
            }
        }
    }

    fun getTeamList(league: String?) {
        view.showProgressBar()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideProgressBar()
                view.showTeamList(data.teams)
            }
        }
    }
}