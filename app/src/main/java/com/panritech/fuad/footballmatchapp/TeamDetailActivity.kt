package com.panritech.fuad.footballmatchapp

import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.panritech.fuad.footballmatchapp.fragment.PlayerItemFragment
import com.panritech.fuad.footballmatchapp.fragment.TeamOverviewFragment
import com.panritech.fuad.footballmatchapp.model.PlayersItem
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.find

class TeamDetailActivity : AppCompatActivity() , PlayerItemFragment.OnListFragmentInteractionListener{
    override fun onListFragmentInteraction(item: PlayersItem) {

    }

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var strTeamId: String = ""
    private var strTeamName: String = ""
    private var strTeamYear: String = ""
    private var strTeamDescription: String = ""

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
        val strTeamBadge = intent.getStringExtra("teamBadge")
        strTeamId = intent.getStringExtra("teamId")
        strTeamName = intent.getStringExtra("teamName")
        strTeamYear = intent.getStringExtra("teamYear")
        strTeamDescription = intent.getStringExtra("teamDescription")

        team_name.text = strTeamName
        team_year.text = strTeamYear

        Picasso.get().load(strTeamBadge).into(teamBadge)
        title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_team_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.add_to_favorite -> {
//                if (isGetDataFinished) {
//                    if (isFavorite) removeFromFavorite()
//                    else addToFavorite()
//
//                    setFavoriteIcon(isFavorite)
//                } else {
//                    toast("Wait Until Finish Displaying Data")
//                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


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
