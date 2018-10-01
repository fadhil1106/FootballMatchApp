package com.panritech.fuad.footballmatchapp.view.team

import com.panritech.fuad.footballmatchapp.model.team.PlayerDetail

interface PlayerDetailView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showPlayersDetail(data: List<PlayerDetail>)
}