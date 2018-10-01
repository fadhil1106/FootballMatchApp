package com.panritech.fuad.footballmatchapp.view.team

import com.panritech.fuad.footballmatchapp.model.team.LeagueItem
import com.panritech.fuad.footballmatchapp.model.team.TeamItem

interface TeamView{
    fun showProgressBar()
    fun hideProgressBar()
    fun showTeamList(data: List<TeamItem>)
    fun showLeagueList(data: List<LeagueItem>)
}