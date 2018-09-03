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
import com.panritech.fuad.footballmatchapp.MatchPresenter
import com.panritech.fuad.footballmatchapp.MatchView
import com.panritech.fuad.footballmatchapp.adapter.MyMatchItemRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.model.MatchItem
import kotlinx.android.synthetic.main.fragment_matchitem.view.*
import org.jetbrains.anko.support.v4.onRefresh

class MatchItemFragment : Fragment(),MatchView {

    private var match: MutableList<MatchItem> = mutableListOf()
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var adapter: MyMatchItemRecyclerViewAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
    override fun showMatchList(data: List<MatchItem>){

        swipeRefresh.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgressBar()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matchitem, container, false)

        //adapter = MyMatchItemRecyclerViewAdapter()
        // Set the adapter

        val recycleView = view.findViewById<RecyclerView>(R.id.listMatch)
        recycleView.layoutManager =  LinearLayoutManager(context)
        adapter = MyMatchItemRecyclerViewAdapter(match, listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        swipeRefresh.onRefresh {
            presenter.getMatchList("")
        }
        showProgressBar()
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, apiRequest, gson)
        presenter.getMatchList("")
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
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

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
