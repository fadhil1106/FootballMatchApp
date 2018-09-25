package com.panritech.fuad.footballmatchapp.view

import com.panritech.fuad.footballmatchapp.model.LeagueItem
import com.panritech.fuad.footballmatchapp.model.TeamItem

interface TeamView{
    fun showProgressBar()
    fun hideProgressBar()
    fun showTeamList(data: List<TeamItem>)
    fun showLeagueList(data: List<LeagueItem>)
}