package com.panritech.fuad.footballmatchapp.view

import com.panritech.fuad.footballmatchapp.model.MatchDetailItem

interface MatchDetailView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showMatchDetail(data: List<MatchDetailItem>)
}