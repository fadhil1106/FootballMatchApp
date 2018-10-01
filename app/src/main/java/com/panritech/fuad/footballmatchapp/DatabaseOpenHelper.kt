package com.panritech.fuad.footballmatchapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.panritech.fuad.footballmatchapp.model.database.Favorite
import com.panritech.fuad.footballmatchapp.model.database.FavoriteTeam
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null)
                instance = DatabaseOpenHelper(ctx.applicationContext)
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteTeam.TABLE_FAVORITES_TEAM, true,
                FavoriteTeam.TEAM_ID to INTEGER + PRIMARY_KEY,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT,
                FavoriteTeam.TEAM_YEAR to TEXT,
                FavoriteTeam.TEAM_DESCRIPTION to TEXT)

        db?.createTable(Favorite.TABLE_FAVORITES, true,
                Favorite.EVENT_ID to INTEGER + PRIMARY_KEY,
                Favorite.MATCH_SCHEDULE to TEXT,
                Favorite.HOME_NAME to TEXT,
                Favorite.AWAY_NAME to TEXT,
                Favorite.HOME_SCORE to TEXT,
                Favorite.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteTeam.TABLE_FAVORITES_TEAM, true)
        db?.dropTable(Favorite.TABLE_FAVORITES, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)

