package com.panritech.fuad.footballmatchapp.model

data class Favorite (val eventId: Int, val matchSchedule: String, val homeName: String, val awayName: String, val homeScore: String, val awayScore: String){

    companion object {
        const val TABLE_FAVORITES: String = "TABLE_FAVORITES"
        const val EVENT_ID: String = "EVENT_ID"
        const val MATCH_SCHEDULE: String = "MATCH_SCHEDULE"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}