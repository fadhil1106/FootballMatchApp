package com.panritech.fuad.footballmatchapp.view

import android.widget.ProgressBar
import com.panritech.fuad.footballmatchapp.model.MatchDetailItem
import com.panritech.fuad.footballmatchapp.model.TeamBadge

interface MatchDetailView {
    fun showProgressBar(progressBar: ProgressBar)
    fun hideProgressBar(progressBar: ProgressBar)
    fun showMatchDetail(data: List<MatchDetailItem>)
    fun showHomeBadge(data: List<TeamBadge>)
    fun showAwayBadge(data: List<TeamBadge>)
}