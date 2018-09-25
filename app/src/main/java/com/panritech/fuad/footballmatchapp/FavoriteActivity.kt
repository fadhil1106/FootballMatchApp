package com.panritech.fuad.footballmatchapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.panritech.fuad.footballmatchapp.fragment.FavoritesMatchFragment
import com.panritech.fuad.footballmatchapp.model.Favorite
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.startActivity

class FavoriteActivity : AppCompatActivity()
        ,FavoritesMatchFragment.OnListFragmentInteractionListener {
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
