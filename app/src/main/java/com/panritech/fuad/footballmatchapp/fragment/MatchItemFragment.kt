package com.panritech.fuad.footballmatchapp.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.panritech.fuad.footballmatchapp.MatchView
import com.panritech.fuad.footballmatchapp.adapter.MyMatchItemRecyclerViewAdapter
import com.panritech.fuad.footballmatchapp.R

import com.panritech.fuad.footballmatchapp.dummy.DummyContent
import com.panritech.fuad.footballmatchapp.dummy.DummyContent.DummyItem
import com.panritech.fuad.footballmatchapp.model.MatchItems

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MatchItemFragment.OnListFragmentInteractionListener] interface.
 */
class MatchItemFragment : Fragment(),MatchView {
    override fun showTeamList(data: List<MatchItems>) {

    }

    // TODO: Customize parameters
    //private var teams: MutableList<>
    private var listener: OnListFragmentInteractionListener? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////        arguments?.let {
////            columnCount = it.getInt(ARG_COLUMN_COUNT)
////        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matchitem, container, false)

        //adapter = MyMatchItemRecyclerViewAdapter()
        // Set the adapter
        val recycleView = view.findViewById<RecyclerView>(R.id.listMatch)
        recycleView.layoutManager =  LinearLayoutManager(context)
        recycleView.adapter = MyMatchItemRecyclerViewAdapter(DummyContent.ITEMS, listener)

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
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() = MatchItemFragment()
    }
}
