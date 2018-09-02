package com.panritech.fuad.footballmatchapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.panritech.fuad.footballmatchapp.fragment.MatchItemFragment.OnListFragmentInteractionListener
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.dummy.DummyContent.DummyItem
import com.panritech.fuad.footballmatchapp.model.MatchItem
import org.jetbrains.anko.find

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMatchItemRecyclerViewAdapter(
        private val items: MutableList<MatchItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyMatchItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_matchitem_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val matchSchedule: TextView = view.find(R.id.matchSchedule)
        val homeTeam: TextView = view.find(R.id.homeTeam)
        val homeScore: TextView = view.find(R.id.homeScore)
        val awayTeam: TextView = view.find(R.id.awayTeam)
        val awayScore: TextView = view.find(R.id.awayScore)

        fun bindItem(items: MatchItem){
            matchSchedule.text = items.matchSchedule
            homeTeam.text = items.homeTeam
            homeScore.text = items.homeScore
            awayTeam.text = items.awayTeam
            awayScore.text = items.awayScore
        }
    }
}