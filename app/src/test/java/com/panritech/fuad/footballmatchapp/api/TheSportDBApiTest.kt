package com.panritech.fuad.footballmatchapp.api

import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class TheSportDBApiTest {

    @Test
    fun getMatch() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun getNextMatch() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}