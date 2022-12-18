package com.example.lista2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object TableInfo : BaseColumns {
    const val TABLE_NAME = "Tasks"
    const val TC_TITLE = "title"
    const val TC_DESCRIPTION = "description"
}

object BasicCommand {
    const val SQL_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS ${TableInfo.TABLE_NAME} (" +
                "id INTEGER PRIMARY KEY, " +
                "${TableInfo.TC_TITLE} TEXT NOT NULL, " +
                "${TableInfo.TC_DESCRIPTION} TEXT NOT NULL)"
   // const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"
}


/*
class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, TableInfo.TABLE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        onCreate(db)
    }

}*/
