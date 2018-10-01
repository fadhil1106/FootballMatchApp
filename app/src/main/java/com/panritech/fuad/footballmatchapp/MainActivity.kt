package com.panritech.fuad.footballmatchapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.panritech.fuad.footballmatchapp.fragment.match.MatchItemFragment
import com.panritech.fuad.footballmatchapp.fragment.match.NextMatchItemFragment
import com.panritech.fuad.footballmatchapp.fragment.team.TeamItemFragment
import com.panritech.fuad.footballmatchapp.model.match.MatchItem
import com.panritech.fuad.footballmatchapp.model.team.TeamItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MatchItemFragment.OnListFragmentInteractionListener
        , NextMatchItemFragment.OnListFragmentInteractionListener
        , TeamItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: TeamItem) {
        startActivity<TeamDetailActivity>("teamId" to item.teamId
                , "teamBadge" to item.teamBadge
                , "teamName" to item.teamName
                , "teamYear" to item.teamYear
                , "teamDescription" to item.teamDescription)
    }

    override fun onListFragmentInteraction(item: MatchItem) {
        startActivity<MatchDetailActivity>("eventId" to item.eventId
                , "homeTeam" to item.homeTeam
                , "awayTeam" to item.awayTeam)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_match -> {
                val fragment = MatchItemFragment.newInstance()
                openFragment(fragment)
                title = "Previous Match"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                val fragment = NextMatchItemFragment.newInstance()
                openFragment(fragment)
                title = "Next Match"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                val fragment = TeamItemFragment.newInstance()
                openFragment(fragment)
                title = "Team"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Previous Match"
        openFragment(MatchItemFragment.newInstance())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        toFavoriteButton.setOnClickListener{
            startActivity<FavoriteActivity>()
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}
