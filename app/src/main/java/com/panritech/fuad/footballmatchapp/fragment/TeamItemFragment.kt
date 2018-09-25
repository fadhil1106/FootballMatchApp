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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.adapter.MyTeamItemRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.api.ApiRepository

import com.panritech.fuad.footballmatchapp.model.LeagueItem
import com.panritech.fuad.footballmatchapp.model.TeamItem
import com.panritech.fuad.footballmatchapp.presenter.TeamPresenter
import com.panritech.fuad.footballmatchapp.view.TeamView
import kotlinx.android.synthetic.main.fragment_teamitem.view.*
import org.jetbrains.anko.support.v4.onRefresh

class TeamItemFragment : Fragment() , TeamView{

    private var teams: MutableList<TeamItem> = mutableListOf()
    private var league: MutableList<LeagueItem> = mutableListOf()
    private var leagueNameList : ArrayList<String> = arrayListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: MyTeamItemRecyclerViewAdapter
    private lateinit var leagueName: String
    private var listener: OnListFragmentInteractionListener? = null

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showLeagueList(data: List<LeagueItem>) {
        for (item in data){
            leagueNameList.add(item.leagueName.toString())
        }

        league.clear()
        league.addAll(data)
        adapter.notifyDataSetChanged()

        leagueName = league[0].leagueName.toString()
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, leagueNameList)
        spinner.adapter = spinnerAdapter
        presenter.getTeamList(leagueName)
    }

    override fun showTeamList(data: List<TeamItem>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgressBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teamitem, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listLeague)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = MyTeamItemRecyclerViewAdapter(teams, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        spinner = view.spinner
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this,request,gson)
        presenter.getLeagueList()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            if (leagueName.isNotEmpty())
                presenter.getTeamList(leagueName)
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
        fun onListFragmentInteraction(item: TeamItem)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TeamItemFragment()
    }
}
