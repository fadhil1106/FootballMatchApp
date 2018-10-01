package com.panritech.fuad.footballmatchapp.view.team

import com.panritech.fuad.footballmatchapp.model.team.PlayersItem

interface PlayerView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showPlayersList(data: List<PlayersItem>)
}