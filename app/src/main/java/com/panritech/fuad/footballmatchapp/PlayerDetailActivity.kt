package com.panritech.fuad.footballmatchapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.team.PlayerDetail
import com.panritech.fuad.footballmatchapp.presenter.team.PlayersDetailPresenter
import com.panritech.fuad.footballmatchapp.view.team.PlayerDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var presenter: PlayersDetailPresenter
    private lateinit var detailProgress: ProgressBar
    private lateinit var imageProgress: ProgressBar
    private var playerId: String = ""

    override fun showProgressBar() {
        detailProgress.visibility = View.VISIBLE
        imageProgress.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        detailProgress.visibility = View.GONE
        imageProgress.visibility = View.GONE
    }

    override fun showPlayersDetail(data: List<PlayerDetail>) {
        player_height.text = data[0].playerHeight
        player_weight.text = data[0].playerWeight
        player_position.text = data[0].playerPosition
        player_description.text = data[0].playerDescription
        Picasso.get().load(data[0].playerImage).into(player_image)
        hideProgressBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        playerId = intent.getStringExtra("playerId")

        val apiRequest = ApiRepository()
        val gson = Gson()

        detailProgress = player_description_progress_bar
        imageProgress = player_image_progress_bar

        showProgressBar()

        presenter = PlayersDetailPresenter(this,apiRequest,gson)
        presenter.getPlayersDetail(playerId)
    }
}
