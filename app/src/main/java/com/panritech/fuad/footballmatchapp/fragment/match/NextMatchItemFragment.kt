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
import android.widget.Spinner
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.adapter.match.MyNextMatchItemRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.match.MatchItem
import com.panritech.fuad.footballmatchapp.model.team.LeagueItem
import com.panritech.fuad.footballmatchapp.presenter.match.MatchPresenter
import com.panritech.fuad.footballmatchapp.view.match.MatchView
import kotlinx.android.synthetic.main.fragment_matchitem.view.*
import org.jetbrains.anko.support.v4.onRefresh

class NextMatchItemFragment : Fragment(), MatchView {
    override fun showLeagueList(data: List<LeagueItem>) {

    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showMatchList(data: List<MatchItem>) {

        swipeRefresh.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgressBar()
    }

    private var match: MutableList<MatchItem> = mutableListOf()
    private var league: MutableList<LeagueItem> = mutableListOf()
    private var leagueNameList : ArrayList<String> = arrayListOf()
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var adapter: MyNextMatchItemRecyclerViewAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var leagueName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nextmatchitem, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listMatch)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyNextMatchItemRecyclerViewAdapter(match, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        swipeRefresh.onRefresh {
            presenter.getNextMatchList("4328")
        }
        showProgressBar()
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, apiRequest, gson)
        presenter.getNextMatchList("4328")
        return view
    }

    fun searchRequest(text: String) {
        adapter.items = match.asSequence().filter { it.awayTeam!!.contains(text) || it.homeTeam!!.contains(text) }.toMutableList()
        adapter.notifyDataSetChanged()
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
        fun onListFragmentInteraction(item: MatchItem)
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() = NextMatchItemFragment()
    }
}
