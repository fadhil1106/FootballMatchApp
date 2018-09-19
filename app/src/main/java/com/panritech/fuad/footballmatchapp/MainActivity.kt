package com.panritech.fuad.footballmatchapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.panritech.fuad.footballmatchapp.fragment.FavoritesMatchFragment
import com.panritech.fuad.footballmatchapp.fragment.MatchItemFragment
import com.panritech.fuad.footballmatchapp.fragment.NextMatchItemFragment
import com.panritech.fuad.footballmatchapp.model.Favorite
import com.panritech.fuad.footballmatchapp.model.MatchItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MatchItemFragment.OnListFragmentInteractionListener
        , NextMatchItemFragment.OnListFragmentInteractionListener
        , FavoritesMatchFragment.OnListFragmentInteractionListener {
    override fun onFavoriteListFragmentInteraction(item: Favorite) {
        startActivity<MatchDetailActivity>("eventId" to item.eventId.toString()
                , "homeTeam" to item.homeName
                , "awayTeam" to item.awayName)
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
                val fragment = FavoritesMatchFragment.newInstance()
                openFragment(fragment)
                title = "Favorites"
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

        toFavoriteButton.setOnClickListener{v: View ->
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
