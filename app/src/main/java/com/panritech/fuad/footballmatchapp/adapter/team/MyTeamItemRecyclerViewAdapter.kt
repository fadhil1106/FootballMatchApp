package com.panritech.fuad.footballmatchapp.adapter.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.panritech.fuad.footballmatchapp.R


import com.panritech.fuad.footballmatchapp.fragment.team.TeamItemFragment.OnListFragmentInteractionListener
import com.panritech.fuad.footballmatchapp.model.team.TeamItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class MyTeamItemRecyclerViewAdapter(
        private val items: MutableList<TeamItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyTeamItemRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_teamitem_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(items: TeamItem){

            Picasso.get().load(items.teamBadge).into(teamBadge)
            teamName.text = items.teamName

            itemView.setOnClickListener {
                mListener?.onListFragmentInteraction(items)
            }
        }
    }
}
