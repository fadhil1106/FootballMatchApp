package com.panritech.fuad.footballmatchapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.panritech.fuad.footballmatchapp.fragment.match.FavoritesMatchFragment
import com.panritech.fuad.footballmatchapp.fragment.team.FavoritesTeamFragment
import com.panritech.fuad.footballmatchapp.model.database.Favorite
import com.panritech.fuad.footballmatchapp.model.database.FavoriteTeam
import com.panritech.fuad.footballmatchapp.model.team.TeamItem
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.startActivity

class FavoriteActivity : AppCompatActivity()
        , FavoritesMatchFragment.OnListFragmentInteractionListener
        , FavoritesTeamFragment.OnListFragmentInteractionListener{
    override fun onListFragmentInteraction(item: FavoriteTeam) {
        Log.e("Team Id ", item.teamId.toString())
        startActivity<TeamDetailActivity>("teamId" to item.teamId.toString()
                , "teamBadge" to item.teamBadge
                , "teamName" to item.teamName
                , "teamYear" to item.teamYear
                , "teamDescription" to item.teamDescription)
    }

    override fun onFavoriteListFragmentInteraction(item: Favorite) {
        startActivity<MatchDetailActivity>("eventId" to item.eventId.toString()
                , "homeTeam" to item.homeName
                , "awayTeam" to item.awayName)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_match -> {
                title = "Favorite Match"
                val fragment = FavoritesMatchFragment.newInstance()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                title = "Favorite TeamItem"
                val fragment = FavoritesTeamFragment.newInstance()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        openFragment(FavoritesMatchFragment.newInstance())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.favorite_container, fragment)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
