package com.panritech.fuad.footballmatchapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.presenter.MatchDetailPresenter
import com.panritech.fuad.footballmatchapp.view.MatchDetailView
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.MatchDetailItem
import com.panritech.fuad.footballmatchapp.model.TeamBadge
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    override fun showHomeBadge(data: List<TeamBadge>) {
        Picasso.get().load(data[0].teamBadge).into(homeBadge)
        hideProgressBar(homeProgress)
    }

    override fun showAwayBadge(data: List<TeamBadge>) {
        Picasso.get().load(data[0].teamBadge).into(awayBadge)
        hideProgressBar(awayProgress)
    }

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var detailProgress: ProgressBar
    private lateinit var homeProgress: ProgressBar
    private lateinit var awayProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val idEvent = intent.getStringExtra("idEvent")
        val homeTeam = intent.getStringExtra("homeTeam")
        val awayTeam = intent.getStringExtra("awayTeam")
        val homeScore = intent.getStringExtra("homeScore")
        val awayScore = intent.getStringExtra("awayScore")
        val matchSchedule = intent.getStringExtra("schedule")

        detailProgress = detailProgressBar
        homeProgress = homeProgressBar
        awayProgress = awayProgessBar

        homeTeamName.text = homeTeam
        homeTeamScore.text = homeScore
        awayTeamName.text = awayTeam
        awayTeamScore.text = awayScore
        eventSchedule.text = matchSchedule

        showProgressBar(detailProgress)
        showProgressBar(homeProgress)
        showProgressBar(awayProgress)
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, apiRequest, gson)
        presenter.getMatchDetail(idEvent)
        presenter.getBadgeUrl(homeTeam,"home")
        presenter.getBadgeUrl(awayTeam,"away")

    }

    override fun showMatchDetail(data: List<MatchDetailItem>) {
        val homeFormation = data[0].homeFormation.toString()
        val homeGoalDetail = getList(data[0].homeGoalDetails,";")
        val homeRedCard =  getList(data[0].homeRedCard,";")
        val homeYellowCard = getList(data[0].homeYellowCard,";")
        val homeGoalkeeper = getList(data[0].homeGoalkeeper,";")
        val homeDefense = getList(data[0].homeDefense,"; ")
        val homeMidfield = getList(data[0].homeMidfield,"; ")
        val homeForward = getList(data[0].homeForward,"; ")
        val homeSubstitutes = getList(data[0].homeSubstitutes,"; ")

        val awayFormation = data[0].awayFormation.toString()
        val awayGoalDetail = getList(data[0].awayGoalDetails,";")
        val awayRedCard = getList(data[0].awayRedCard,";")
        val awayYellowCard = getList(data[0].awayYellowCard,";")
        val awayGoalkeeper = getList(data[0].awayGoalkeeper,";")
        val awayDefense = getList(data[0].awayDefense,"; ")
        val awayMidfield = getList(data[0].awayMidfield,"; ")
        val awayForward = getList(data[0].awayForward,"; ")
        val awaySubstitutes = getList(data[0].awaySubstitutes,"; ")

        txtHomeFormation.text = getString("",homeFormation)
        setDetailText(homeGoalDetail, txtHomeGoals)
        setDetailText(homeRedCard, txtHomeRedCard)
        setDetailText(homeYellowCard, txtHomeYellowCard)
        setDetailText(homeGoalkeeper, txtHomeGoalKeeper)
        setDetailText(homeDefense, txtHomeDefense)
        setDetailText(homeMidfield, txtHomeMidfield)
        setDetailText(homeForward, txtHomeForward)
        setDetailText(homeSubstitutes, txtHomeSubstitutes)

        txtAwayFormation.text = getString("",awayFormation)
        setDetailText(awayGoalDetail, txtAwayGoals)
        setDetailText(awayRedCard, txtAwayRedCard)
        setDetailText(awayYellowCard, txtAwayYellowCard)
        setDetailText(awayGoalkeeper, txtAwayGoalKeeper)
        setDetailText(awayDefense, txtAwayDefense)
        setDetailText(awayMidfield, txtAwayMidfield)
        setDetailText(awayForward, txtAwayForward)
        setDetailText(awaySubstitutes, txtAwaySubstitutes)

        hideProgressBar(detailProgress)
    }

    override fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    private fun getList(details: String?, delimiters: String): List<String> {
        return details.toString().split(delimiters)
    }

    private fun getString(text: String, value: String): String {
        return if (value!= "null")
            getString(R.string.detail_text, text, value)
        else
            getString(R.string.detail_text,"","No Data")
    }

    private fun setDetailText(list: List<String>, txtView: TextView) {
        for (value in list){ txtView.text = getString(txtView.text.toString(), value) }
    }
}
