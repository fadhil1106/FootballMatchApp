package com.panritech.fuad.footballmatchapp.presenter

import com.google.gson.Gson
import com.panritech.fuad.footballmatchapp.provider.TestContextProvider
import com.panritech.fuad.footballmatchapp.api.ApiRepository
import com.panritech.fuad.footballmatchapp.api.TheSportDBApi
import com.panritech.fuad.footballmatchapp.model.MatchItem
import com.panritech.fuad.footballmatchapp.model.MatchItemResponse
import com.panritech.fuad.footballmatchapp.view.MatchView
import kotlinx.coroutines.experimental.launch
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var matchView: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var theSportDBApi: TheSportDBApi

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(matchView,apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testGetMatchList() {
        val match: MutableList<MatchItem> = mutableListOf()
        val response = MatchItemResponse(match)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
            .doRequest(theSportDBApi.getMatch(league))
            ,MatchItemResponse::class.java)).thenReturn(response)

        presenter.getMatchList(league)

        launch {
            verify(matchView).showProgressBar()
            verify(matchView).showMatchList(match)
            verify(matchView).hideProgressBar()
        }
    }

    @Test
    fun testGetNextMatchList() {
        val nextMatch: MutableList<MatchItem> = mutableListOf()
        val response = MatchItemResponse(nextMatch)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(theSportDBApi.getNextMatch(league))
                ,MatchItemResponse::class.java)).thenReturn(response)

        presenter.getNextMatchList(league)

        launch {
            verify(matchView).showProgressBar()
            verify(matchView).showMatchList(nextMatch)
            verify(matchView).hideProgressBar()
        }
    }
}