package com.panritech.fuad.footballmatchapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.panritech.fuad.footballmatchapp.model.Favorite
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

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
        db?.dropTable(Favorite.TABLE_FAVORITES, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)

