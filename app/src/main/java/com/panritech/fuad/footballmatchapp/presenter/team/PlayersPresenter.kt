package com.panritech.fuad.footballmatchapp.presenter.team

import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.team.PlayersItemResponse
import com.panritech.fuad.footballmatchapp.view.team.PlayerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayersPresenter (val view: PlayerView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson){
    fun getPlayerList(teamId: String?) {
        view.showProgressBar()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPlayerList(teamId)),
                    PlayersItemResponse::class.java
            )

            uiThread {
                view.hideProgressBar()
                view.showPlayersList(data.player)
            }
        }
    }
}