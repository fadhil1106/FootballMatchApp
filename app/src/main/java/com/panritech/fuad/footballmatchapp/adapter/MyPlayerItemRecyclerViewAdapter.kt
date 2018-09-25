package com.panritech.fuad.footballmatchapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.panritech.fuad.footballmatchapp.R
import com.panritech.fuad.footballmatchapp.fragment.PlayerItemFragment.OnListFragmentInteractionListener
import com.panritech.fuad.footballmatchapp.model.PlayersItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class MyPlayerItemRecyclerViewAdapter(
        private val items: MutableList<PlayersItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyPlayerItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_playeritem_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val playerImage: ImageView = view.find(R.id.player_image)
        private val playerName: TextView = view.find(R.id.player_name)

        fun bindItem(items: PlayersItem){
            Picasso.get().load(items.playerImage).into(playerImage)
            playerName.text = items.playerName

            itemView.setOnClickListener {
                mListener?.onListFragmentInteraction(items)
            }
        }
    }
}
