package com.panritech.fuad.footballmatchapp

import com.panritech.fuad.footballmatchapp.model.MatchItems

interface MatchView {
    fun showTeamList(data: List<MatchItems>)
}