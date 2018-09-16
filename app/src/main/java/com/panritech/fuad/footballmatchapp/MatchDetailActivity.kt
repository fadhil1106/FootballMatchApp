package com.panritech.fuad.footballmatchapp

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.Favorite
import com.panritech.fuad.footballmatchapp.model.MatchDetailItem
import com.panritech.fuad.footballmatchapp.model.TeamBadge
import com.panritech.fuad.footballmatchapp.presenter.MatchDetailPresenter
import com.panritech.fuad.footballmatchapp.view.MatchDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    override fun showHomeBadge(data: List<TeamBadge>) {
        Picasso.get().load(data[0].teamBadge).into(homeBadge)
        hideProgressBar(homeProgress)
        homeBadge.visibility = View.VISIBLE
    }

    override fun showAwayBadge(data: List<TeamBadge>) {
        Picasso.get().load(data[0].teamBadge).into(awayBadge)
        hideProgressBar(awayProgress)
        awayBadge.visibility = View.VISIBLE
    }

    private val detail: MutableList<MatchDetailItem> = mutableListOf()

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var detailProgress: ProgressBar
    private lateinit var homeProgress: ProgressBar
    private lateinit var awayProgress: ProgressBar

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var isGetDataFinished = false

    private var idEvent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idEvent = intent.getStringExtra("eventId")
        val homeTeam = intent.getStringExtra("homeTeam")
        val awayTeam = intent.getStringExtra("awayTeam")
        checkFavorite()

        detailProgress = detailProgressBar
        homeProgress = homeProgressBar
        awayProgress = awayProgessBar

        homeTeamName.text = homeTeam
        awayTeamName.text = awayTeam

        showProgressBar(detailProgress)
        showProgressBar(homeProgress)
        showProgressBar(awayProgress)
        val apiRequest = ApiRepository()
        val gson = Gson()
        isGetDataFinished = false
        presenter = MatchDetailPresenter(this, apiRequest, gson)
        presenter.run {
            getMatchDetail(idEvent)
            getBadgeUrl(homeTeam, "home")
            getBadgeUrl(awayTeam, "away")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavoriteIcon(isFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.add_to_favorite -> {
                if (isGetDataFinished) {
                    if (isFavorite) removeFromFavorite()
                    else addToFavorite()

                    setFavoriteIcon(isFavorite)
                } else {
                    toast("Wait Until Finish Displaying Data")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMatchDetail(data: List<MatchDetailItem>) {
        detail.clear()
        detail.addAll(data)

        var homeScore = data[0].homeScore.toString()
        var awayScore = data[0].awayScore.toString()

        if (data[0].homeScore.toString() == "null") homeScore = ""

        if (data[0].awayScore.toString() == "null") awayScore = ""

        val matchSchedule = data[0].matchSchedule.toString()

        val homeFormation = data[0].homeFormation.toString().trim()
        val homeGoalDetail = getList(data[0].homeGoalDetails)
        val homeRedCard = getList(data[0].homeRedCard)
        val homeYellowCard = getList(data[0].homeYellowCard)
        val homeGoalkeeper = getList(data[0].homeGoalkeeper)
        val homeDefense = getList(data[0].homeDefense)
        val homeMidfield = getList(data[0].homeMidfield)
        val homeForward = getList(data[0].homeForward)
        val homeSubstitutes = getList(data[0].homeSubstitutes)

        val awayFormation = data[0].awayFormation.toString().trim()
        val awayGoalDetail = getList(data[0].awayGoalDetails)
        val awayRedCard = getList(data[0].awayRedCard)
        val awayYellowCard = getList(data[0].awayYellowCard)
        val awayGoalkeeper = getList(data[0].awayGoalkeeper)
        val awayDefense = getList(data[0].awayDefense)
        val awayMidfield = getList(data[0].awayMidfield)
        val awayForward = getList(data[0].awayForward)
        val awaySubstitutes = getList(data[0].awaySubstitutes)


        homeTeamScore.text = homeScore
        awayTeamScore.text = awayScore
        eventSchedule.text = matchSchedule

        txtHomeFormation.text = getString("", homeFormation)
        setDetailText(homeGoalDetail, txtHomeGoals)
        setDetailText(homeRedCard, txtHomeRedCard)
        setDetailText(homeYellowCard, txtHomeYellowCard)
        setDetailText(homeGoalkeeper, txtHomeGoalKeeper)
        setDetailText(homeDefense, txtHomeDefense)
        setDetailText(homeMidfield, txtHomeMidfield)
        setDetailText(homeForward, txtHomeForward)
        setDetailText(homeSubstitutes, txtHomeSubstitutes)

        txtAwayFormation.text = getString("", awayFormation)
        setDetailText(awayGoalDetail, txtAwayGoals)
        setDetailText(awayRedCard, txtAwayRedCard)
        setDetailText(awayYellowCard, txtAwayYellowCard)
        setDetailText(awayGoalkeeper, txtAwayGoalKeeper)
        setDetailText(awayDefense, txtAwayDefense)
        setDetailText(awayMidfield, txtAwayMidfield)
        setDetailText(awayForward, txtAwayForward)
        setDetailText(awaySubstitutes, txtAwaySubstitutes)
        isGetDataFinished = true
        hideProgressBar(detailProgress)
        detailTable.visibility = View.VISIBLE
    }

    override fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    private fun setFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            menuItem?.findItem(R.id.add_to_favorite)?.icon = getDrawable(R.drawable.like_filled_icon)
        } else {
            menuItem?.findItem(R.id.add_to_favorite)?.icon = getDrawable(R.drawable.like_icon)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITES,
                        Favorite.EVENT_ID to idEvent.toInt(),
                        Favorite.MATCH_SCHEDULE to detail[0].matchSchedule.toString(),
                        Favorite.HOME_NAME to detail[0].homeTeam.toString(),
                        Favorite.AWAY_NAME to detail[0].awayTeam.toString(),
                        Favorite.HOME_SCORE to detail[0].homeScore.toString(),
                        Favorite.AWAY_SCORE to detail[0].awayScore.toString())
            }
            snackbar(detailRootLayout, "Added to Favorites").show()
            isFavorite = true
        } catch (e: SQLiteConstraintException) {
            snackbar(detailRootLayout, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITES
                        , "(EVENT_ID = {eventId})"
                        , "eventId" to idEvent)
            }
            snackbar(detailRootLayout, "Removed From Favorites").show()
            isFavorite = false
        } catch (e: SQLiteConstraintException) {
            snackbar(detailRootLayout, e.localizedMessage)
        }
    }

    private fun checkFavorite() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITES)
                    .whereArgs("(EVENT_ID = {eventId})", "eventId" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun getList(details: String?): List<String> {
        return details.toString().split(";")
    }

    private fun getString(text: String, value: String): String {
        return if (value != "null")
            getString(R.string.detail_text, text, value)
        else
            getString(R.string.detail_text, "", "")
    }

    private fun setDetailText(list: List<String>, txtView: TextView) {
        list.forEach { value ->
            txtView.text = getString(txtView.text.toString(), value.trim())
        }
    }
}
