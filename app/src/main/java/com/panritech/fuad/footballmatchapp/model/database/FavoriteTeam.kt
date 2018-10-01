package com.panritech.fuad.footballmatchapp.model.database

class FavoriteTeam(val teamId: Int, val teamName: String, val teamBadge: String, val teamYear: String, val teamDescription:String) {

    companion object {
        const val TABLE_FAVORITES_TEAM: String = "TABLE_FAVORITES_TEAM"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_YEAR: String = "TEAM_YEAR"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
    }
}