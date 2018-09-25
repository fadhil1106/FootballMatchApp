package com.panritech.fuad.footballmatchapp.view

import com.panritech.fuad.footballmatchapp.model.PlayersItem

interface PlayerView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showPlayersList(data: List<PlayersItem>)
}