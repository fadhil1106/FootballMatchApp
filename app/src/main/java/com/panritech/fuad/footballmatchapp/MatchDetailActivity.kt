package com.panritech.fuad.footballmatchapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.widget.ProgressBar
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.presenter.MatchDetailPresenter
import com.panritech.fuad.footballmatchapp.view.MatchDetailView
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.MatchDetailItem
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMatchDetail(data: List<MatchDetailItem>) {

        val homeFormation = data[0].homeFormation.toString()
        val homeGoalDetail = data[0].homeGoalDetails.toString().split("; ")
        val homeRedCard = data[0].homeRedCard.toString().split(";")
        val homeYellowCard = data[0].homeYellowCard.toString().split(";")
        val homeGoalkeeper = data[0].homeGoalkeeper.toString().split(";")
        val homeMidfield = data[0].homeMidfield.toString().split(";")
        val awayGoalDetail = data[0].awayGoalDetails.toString().split("; ")

        for (value in homeGoalDetail){
            val goals = homeGoals.text
            homeGoals.text = "$goals" + "\n" + "$value"
        }
    }


    private lateinit var presenter: MatchDetailPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
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
}
