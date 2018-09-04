package com.panritech.fuad.footballmatchapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.panritech.fuad.footballmatchapp.fragment.NextMatchItemFragment.OnListFragmentInteractionListener
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.model.MatchItem
import org.jetbrains.anko.find

class MyNextMatchItemRecyclerViewAdapter(
        private val items: MutableList<MatchItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyNextMatchItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_nextmatchitem_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val matchSchedule: TextView = view.find(R.id.matchSchedule)
        val homeTeam: TextView = view.find(R.id.homeTeam)
        val homeScore: TextView = view.find(R.id.homeTeamScore)
        val awayTeam: TextView = view.find(R.id.awayTeam)
        val awayScore: TextView = view.find(R.id.awayTeamScore)

        fun bindItem(items: MatchItem){
            matchSchedule.text = items.matchSchedule
            homeTeam.text = items.homeTeam
            homeScore.text = items.homeScore
            awayTeam.text = items.awayTeam
            awayScore.text = items.awayScore

            itemView.setOnClickListener {
                mListener?.onListFragmentInteraction(items)
            }
        }
    }
}
