package com.panritech.fuad.footballmatchapp

import com.panritech.fuad.footballmatchapp.model.MatchItem

interface MatchView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showMatchList(data: List<MatchItem>)
}