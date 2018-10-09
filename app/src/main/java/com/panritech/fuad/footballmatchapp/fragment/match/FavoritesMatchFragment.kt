package com.panritech.fuad.footballmatchapp.fragment.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.adapter.match.MyFavoritesRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.database
import com.panritech.fuad.footballmatchapp.model.database.Favorite

import com.panritech.fuad.footballmatchapp.model.match.MatchItem
import com.panritech.fuad.footballmatchapp.model.team.LeagueItem
import com.panritech.fuad.footballmatchapp.view.match.MatchView
import kotlinx.android.synthetic.main.fragment_matchitem.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

class FavoritesMatchFragment : Fragment(), MatchView {
    override fun showLeagueList(data: List<LeagueItem>) {}

    private var match: MutableList<Favorite> = mutableListOf()
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var adapter: MyFavoritesRecyclerViewAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showMatchList(data: List<MatchItem>) {}

    private fun showFavorites() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITES)
            val favorite = result.parseList(classParser<Favorite>())
            match.clear()
            match.addAll(favorite)
            adapter.notifyDataSetChanged()
            hideProgressBar()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites_match, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listMatch)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyFavoritesRecyclerViewAdapter(match, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        swipeRefresh.onRefresh {
            showFavorites()
        }
        showProgressBar()

        showFavorites()

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
        fun onFavoriteListFragmentInteraction(item: Favorite)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesMatchFragment()
    }
}
