package com.panritech.fuad.footballmatchapp.presenter

import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.provider.CoroutineContextProvider
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.MatchItemResponse
import com.panritech.fuad.footballmatchapp.view.MatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val matchView: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getMatchList(league: String?) {

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getMatch(league))
                        , MatchItemResponse::class.java
                )
            }
            matchView.showMatchList(data.await().events)
        }
//        doAsync {
//            val data = gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getMach(league))
//                    , MatchItemResponse::class.java
//            )
//
//            uiThread {
//                matchView.showMatchList(data.events)
//            }
//        }
    }

    fun getNextMatchList(league: String?) {
        async (context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextMatch(league))
                        , MatchItemResponse::class.java)
            }
            matchView.showMatchList(data.await().events)
        }
    }
}