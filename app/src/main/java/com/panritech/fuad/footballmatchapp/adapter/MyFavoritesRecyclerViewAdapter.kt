package com.panritech.fuad.footballmatchapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.panritech.fuad.footballmatchapp.R


import com.panritech.fuad.footballmatchapp.fragment.FavoritesFragment.OnListFragmentInteractionListener
import com.panritech.fuad.footballmatchapp.model.Favorite
import org.jetbrains.anko.find

class MyFavoritesRecyclerViewAdapter(
        private val items: MutableList<Favorite>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyFavoritesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_favorites_list, parent, false)
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

        fun bindItem(items: Favorite) {

            setText(matchSchedule, items.matchSchedule)
            setText(homeTeam, items.homeName)
            setText(homeScore, items.homeScore)
            setText(awayTeam, items.awayName)
            setText(awayScore, items.awayScore)

            itemView.setOnClickListener {
                mListener?.onFavoriteListFragmentInteraction(items)
            }
        }

        private fun setText(textView: TextView, value: String) {
            if (value == "null")
                textView.visibility = View.GONE
            else
                textView.text = value
        }
    }
}
