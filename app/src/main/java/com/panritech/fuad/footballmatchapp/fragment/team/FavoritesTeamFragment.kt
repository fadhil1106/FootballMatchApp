package com.panritech.fuad.footballmatchapp.fragment.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.adapter.team.MyFavoritesTeamRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.database
import com.panritech.fuad.footballmatchapp.model.database.Favorite
import com.panritech.fuad.footballmatchapp.model.database.FavoriteTeam

import com.panritech.fuad.footballmatchapp.model.team.LeagueItem
import com.panritech.fuad.footballmatchapp.model.team.TeamItem
import com.panritech.fuad.footballmatchapp.presenter.team.TeamPresenter
import com.panritech.fuad.footballmatchapp.view.team.TeamView
import kotlinx.android.synthetic.main.fragment_teamitem.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FavoritesTeamFragment.OnListFragmentInteractionListener] interface.
 */
class FavoritesTeamFragment : Fragment(), TeamView{

    private var teams: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: MyFavoritesTeamRecyclerViewAdapter
    private var listener: OnListFragmentInteractionListener? = null

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showLeagueList(data: List<LeagueItem>) {
    }

    override fun showTeamList(data: List<TeamItem>) {}

    private fun showFavorites() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITES_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            teams.clear()
            teams.addAll(favorite)
            adapter.notifyDataSetChanged()
            hideProgressBar()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favoritesteam, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listLeague)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyFavoritesTeamRecyclerViewAdapter(teams, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        showFavorites()

        swipeRefresh.onRefresh {
            showFavorites()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: FavoriteTeam)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesTeamFragment()
    }
}
