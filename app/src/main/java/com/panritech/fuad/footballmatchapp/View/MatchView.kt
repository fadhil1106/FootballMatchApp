package com.panritech.fuad.footballmatchapp.View

import com.panritech.fuad.footballmatchapp.model.MatchItem

interface MatchView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showMatchList(data: List<MatchItem>)
}