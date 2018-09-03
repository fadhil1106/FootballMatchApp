package com.panritech.fuad.footballmatchapp

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.panritech.fuad.footballmatchapp.fragment.MatchItemFragment
import com.panritech.fuad.footballmatchapp.fragment.NextMatchItemFragment
import com.panritech.fuad.footballmatchapp.dummy.DummyContent
import com.panritech.fuad.footballmatchapp.model.MatchItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MatchItemFragment.OnListFragmentInteractionListener, NextMatchItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: MatchItem) {
        toast(item.homeTeam.toString())
        startActivity<MatchDetail>()
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
       toast(item.toString())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_match -> {
                //message.setText(R.string.title_match)
                val fragment = MatchItemFragment.newInstance()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                //message.setText(R.string.title_next_match)
                val fragment = NextMatchItemFragment.newInstance()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(MatchItemFragment.newInstance())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
