package com.panritech.fuad.footballmatchapp.presenter.team

import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.team.PlayerDetailResponse
import com.panritech.fuad.footballmatchapp.view.team.PlayerDetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayersDetailPresenter (val view: PlayerDetailView,
                              private val apiRepository: ApiRepository,
                              private val gson: Gson){
    fun getPlayersDetail(playerId: String) {
        view.showProgressBar()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPlayerDetails(playerId)),
                    PlayerDetailResponse::class.java
            )

            uiThread {
                view.showPlayersDetail(data.players)
            }
        }
    }

}