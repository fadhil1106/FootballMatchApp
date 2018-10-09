package com.panritech.fuad.footballmatchapp.fragment.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.adapter.match.MyMatchItemRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.match.MatchItem
import com.panritech.fuad.footballmatchapp.model.team.LeagueItem
import com.panritech.fuad.footballmatchapp.presenter.match.MatchPresenter
import com.panritech.fuad.footballmatchapp.view.match.MatchView
import kotlinx.android.synthetic.main.fragment_matchitem.view.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.onRefresh

class MatchItemFragment : Fragment(), MatchView {
    private var match: MutableList<MatchItem> = mutableListOf()
    private var league: MutableList<LeagueItem> = mutableListOf()
    private var leagueNameList : ArrayList<String> = arrayListOf()
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var adapter: MyMatchItemRecyclerViewAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var leagueName: String

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

    override fun showLeagueList(data: List<LeagueItem>) {
        for (item in data){
            leagueNameList.add(item.leagueName.toString())
        }

        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()

        leagueName = league[0].leagueId.toString()
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, leagueNameList)
        spinner.adapter = spinnerAdapter
        presenter.getMatchList(leagueName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matchitem, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listMatch)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyMatchItemRecyclerViewAdapter(match, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        spinner = view.spinner

        showProgressBar()
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, apiRequest, gson)

        presenter.getLeagueList()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = league[position].leagueId.toString()
                presenter.getMatchList(leagueName)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            if (leagueName.isNotEmpty())
                presenter.getMatchList(leagueName)
        }
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
        fun onListFragmentInteraction(item: MatchItem)
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() = MatchItemFragment()
    }
}
