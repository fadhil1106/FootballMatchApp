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
import com.panritech.fuad.footballmatchapp.fragment.TeamOverviewFragment
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.find

class TeamDetailActivity : AppCompatActivity() {
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

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
        Picasso.get().load(strTeamBadge).into(teamBadge)
        title = ""

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_team_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.item_favorite) {
            return true
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
                1 -> TeamOverviewFragment.newInstance()
                else -> TeamOverviewFragment.newInstance()
            }
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    class PlaceholderFragment : Fragment() {
//
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                                  savedInstanceState: Bundle?): View? {
//            return inflater.inflate(R.layout.fragment_team_detail, container, false)
//        }
//
////        companion object {
////            /**
////             * The fragment argument representing the section number for this
////             * fragment.
////             */
////            private val ARG_SECTION_NUMBER = "section_number"
////
////            /**
////             * Returns a new instance of this fragment for the given section
////             * number.
////             */
////            fun newInstance(sectionNumber: Int): PlaceholderFragment {
////                val fragment = PlaceholderFragment()
////                val args = Bundle()
////                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
////                fragment.arguments = args
////                return fragment
////            }
////        }
//    }
}
