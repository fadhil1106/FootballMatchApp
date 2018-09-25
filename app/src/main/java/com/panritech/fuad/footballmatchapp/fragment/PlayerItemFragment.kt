package com.panritech.fuad.footballmatchapp.fragment

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
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.adapter.MyPlayerItemRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.api.ApiRepository

import com.panritech.fuad.footballmatchapp.model.PlayersItem
import com.panritech.fuad.footballmatchapp.presenter.PlayersPresenter
import com.panritech.fuad.footballmatchapp.view.PlayerView
import kotlinx.android.synthetic.main.fragment_teamitem.view.*
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PlayerItemFragment.OnListFragmentInteractionListener] interface.
 */
class PlayerItemFragment : Fragment() ,PlayerView{
    private var players: MutableList<PlayersItem> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: MyPlayerItemRecyclerViewAdapter
    private var listener: OnListFragmentInteractionListener? = null
    private var teamId: String = ""

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showPlayersList(data: List<PlayersItem>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgressBar()
        swipeRefresh.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            teamId = it.getString(ARG_TEAM_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playeritem, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listPlayers)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyPlayerItemRecyclerViewAdapter(players, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayersPresenter(this,request,gson)
        presenter.getPlayerList(teamId)

        swipeRefresh.onRefresh {
            presenter.getPlayerList(teamId)
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
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: PlayersItem)
    }

    companion object {

        const val ARG_TEAM_ID = ""

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(strTeamId: String) = PlayerItemFragment().apply {
            arguments = Bundle().apply {
                putString(PlayerItemFragment.ARG_TEAM_ID, strTeamId)
            }
        }
    }
}
