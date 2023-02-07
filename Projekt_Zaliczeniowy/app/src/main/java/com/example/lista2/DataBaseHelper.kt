package com.example.lista2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object TableInfo : BaseColumns {
    const val TABLE_NAME = "Tasks"
    const val TC_TITLE = "title"
    const val TC_DESCRIPTION = "description"
    const val TC_DATE = "date"
}

object DayTableInfo : BaseColumns{
    const val TABLE_NAME = "Days"
    const val TC_DAY = "Day"
    const val TC_MONTH = "Month"
    const val TC_YEAR = "Year"
    const val TC_RATE = "Rate"
}

object BasicCommand {
    const val SQL_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS ${TableInfo.TABLE_NAME} (" +
                "id INTEGER PRIMARY KEY, " +
                "${TableInfo.TC_TITLE} TEXT NOT NULL, " +
                "${TableInfo.TC_DESCRIPTION} TEXT NOT NULL, " + "${TableInfo.TC_DATE} TEXT NOT NULL)"

    const val SQL_CREATE_DAYTABLE =
        "CREATE TABLE IF NOT EXISTS ${DayTableInfo.TABLE_NAME} (" +
                "id INTEGER PRIMARY KEY, " +
                "${DayTableInfo.TC_DAY} TEXT NOT NULL, " +
                "${DayTableInfo.TC_MONTH} TEXT NOT NULL, " +
                "${DayTableInfo.TC_YEAR} TEXT NOT NULL, " +
                "${DayTableInfo.TC_RATE} INT NOT NULL)"
}

