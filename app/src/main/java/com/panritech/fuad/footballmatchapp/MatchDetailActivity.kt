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
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val idEvent = intent.getStringExtra("idEvent")
        val homeTeam = intent.getStringExtra("homeTeam")
        val awayTeam = intent.getStringExtra("awayTeam")
        val homeScore = intent.getStringExtra("homeScore")
        val awayScore = intent.getStringExtra("awayScore")
        val matchSchedule = intent.getStringExtra("schedule")

        progressBar = detailProgressBar

        homeTeamName.text = homeTeam
        homeTeamScore.text = homeScore
        awayTeamName.text = awayTeam
        awayTeamScore.text = awayScore
        eventSchedule.text = matchSchedule

        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, apiRequest, gson)
        presenter.getMatchDetail(idEvent)
        Log.e("Call: ","Call Presenter")
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
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun getList(details: String?, delimiters: String): List<String> {
        return details.toString().split(delimiters)
    }

    private fun getString(text: String, value: String): String =
            getString(R.string.detail_text,text,value)

    private fun setDetailText(list: List<String>, txtView: TextView) {
        for (value in list){txtView.text = getString(txtView.text.toString(), value)}
    }
}
