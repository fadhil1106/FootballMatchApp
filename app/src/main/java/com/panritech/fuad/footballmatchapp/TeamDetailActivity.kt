package com.panritech.fuad.footballmatchapp

import android.database.sqlite.SQLiteConstraintException
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.panritech.fuad.footballmatchapp.fragment.team.PlayerItemFragment
import com.panritech.fuad.footballmatchapp.fragment.team.TeamOverviewFragment
import com.panritech.fuad.footballmatchapp.model.database.FavoriteTeam
import com.panritech.fuad.footballmatchapp.model.team.PlayersItem
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TeamDetailActivity : AppCompatActivity() , PlayerItemFragment.OnListFragmentInteractionListener{
    override fun onListFragmentInteraction(item: PlayersItem) {
        startActivity<PlayerDetailActivity>("playerId" to item.playerId)
    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var menuItem: Menu? = null
    private var strTeamId: String = ""
    private var strTeamName: String = ""
    private var strTeamYear: String = ""
    private var strTeamBadge: String = ""
    private var strTeamDescription: String = ""
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        val teamBadge: ImageView = find(R.id.team_badge)
        strTeamBadge = intent.getStringExtra("teamBadge")
        strTeamId = intent.getStringExtra("teamId")
        strTeamName = intent.getStringExtra("teamName")
        strTeamYear = intent.getStringExtra("teamYear")
        strTeamDescription = intent.getStringExtra("teamDescription")
//        checkFavorite()

        team_name.text = strTeamName
        team_year.text = strTeamYear

        Picasso.get().load(strTeamBadge).into(teamBadge)
        title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_team_detail, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()
                setFavoriteIcon(isFavorite)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            menuItem?.findItem(R.id.add_to_favorite)?.icon = getDrawable(R.drawable.heart_outline_filled)
        } else {
            menuItem?.findItem(R.id.add_to_favorite)?.icon = getDrawable(R.drawable.heart_outline)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITES_TEAM,
                        FavoriteTeam.TEAM_ID to strTeamId.toInt(),
                        FavoriteTeam.TEAM_NAME to strTeamName,
                        FavoriteTeam.TEAM_BADGE to strTeamBadge,
                        FavoriteTeam.TEAM_YEAR to strTeamYear,
                        FavoriteTeam.TEAM_DESCRIPTION to strTeamDescription)
            }
            snackbar(main_content, "Added to Favorites").show()
            isFavorite = true
        } catch (e: SQLiteConstraintException) {
            snackbar(main_content, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITES_TEAM
                        , "(TEAM_ID = {strTeamId})"
                        , "strTeamId" to strTeamId)
            }
            snackbar(main_content, "Removed From Favorites").show()
            isFavorite = false
        } catch (e: SQLiteConstraintException) {
            snackbar(main_content, e.localizedMessage)
        }
    }

//    private fun checkFavorite() {
//        try {
//            database.use {
//                val result = select(FavoriteTeam.TABLE_FAVORITES_TEAM)
//                        .whereArgs("(TEAM_ID = {strTeamId})", "strTeamId" to strTeamId)
//                val favorite = result.parseList(classParser<FavoriteTeam>())
//                if (!favorite.isEmpty()) isFavorite = true
//            }
//        }catch (e: SQLiteConstraintException){
//            snackbar(main_content, e.localizedMessage)
//        }
//
//    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return when (position) {
                1 -> PlayerItemFragment.newInstance(strTeamId)
                else -> TeamOverviewFragment.newInstance(strTeamDescription)
            }
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }
    }
}
