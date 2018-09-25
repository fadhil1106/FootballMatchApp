package com.panritech.fuad.footballmatchapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.panritech.fuad.footballmatchapp.R
import kotlinx.android.synthetic.main.fragment_team_overview.view.*

class TeamOverviewFragment : Fragment() {

    private var description: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            description = it.getString(ARG_DESCRIPTION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team_overview, container, false)

        view.team_description.text = description

        return view

    }

    companion object {

        const val ARG_DESCRIPTION = "description"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(description: String) = TeamOverviewFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_DESCRIPTION, description)
            }
        }
    }

}
