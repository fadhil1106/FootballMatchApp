package com.panritech.fuad.footballmatchapp.presenter

import android.util.Log
import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.view.MatchDetailView
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.MatchDetailItemResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val matchDetailView: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson)
{
    fun getMatchDetail(events: String?){
        Log.e("Presenter: ","Called")

        doAsync {
            Log.e("GetData: ","Getting Data")
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatchDetail(events))
                    , MatchDetailItemResponse::class.java)
            Log.e("GetData: ",data.toString())
            uiThread {
                matchDetailView.showMatchDetail(data.events)
            }
        }
    }
}